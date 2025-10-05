package com.healthcare.healingxpert.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.healingxpert.util.AppUtils;
import com.healthcare.healingxpert.util.ImageUtils;
import com.healthcare.healingxpert.model.CartItems;
import com.healthcare.healingxpert.model.Medicine;
import com.healthcare.healingxpert.repository.CartItemsRepository;
import com.healthcare.healingxpert.repository.MedicineRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class MedicineService {

	@Autowired
	MedicineRepository medicineRepository;

	@Autowired
	CartItemsRepository cartItemsRepository;

	public void saveMedicine(Medicine medicine) throws ParseException {
		log.info("Inside saveMedicine() start");
		if (null != medicine) {
			if (null != medicine.getExpiryDateString() && !medicine.getExpiryDateString().isBlank()) {
				medicine.setExpiryDate(AppUtils.convertDateStringToDate(medicine.getExpiryDateString()));
			}
			if (medicine.getQuantityPerUnit() > 0 && medicine.getMedicinePrice() > 0) {
				medicine.setPricePerUnit(medicine.getQuantityPerUnit() * medicine.getMedicinePrice());
			}
			medicine.setVersion(AppUtils.VERSION);
			medicine.setCreatedBy(AppUtils.getName());
			medicine.setCreatedDate(AppUtils.getTimestamp());
			medicineRepository.save(medicine);
		}
		log.info("Inside saveMedicine() end");
	}

	public List<Medicine> getMedicines() {
		log.info("Inside getMedicines() start");
		List<Medicine> medicineList = medicineRepository.findAll();
		for (int i = 0; i < medicineList.size(); i++) {
			try {
				if (null != medicineList.get(i) && null != medicineList.get(i).getImageData()) {
					medicineList.get(i).setImageData(ImageUtils.decompressImage(medicineList.get(i).getImageData()));
				}
				if (null != medicineList.get(i) && null != medicineList.get(i).getExpiryDate()) {
					medicineList.get(i).setExpiryDateString(medicineList.get(i).getExpiryDate().toString());
				}
			} catch (Exception e) {
				log.error("Inside getMedicines error::" + e);
			}
		}
		log.info("Inside getMedicines() end");
		return medicineList;
	}

	public Medicine getMedicineById(int id) {
		log.info("Inside getMedicineById() start");
		return medicineRepository.findById(id).get();
	}

	public void deleteMedicineById(int id) {
		log.info("Inside deleteMedicineById() start");
		medicineRepository.deleteById(id);
	}

	public void saveCartItems(List<CartItems> cartItemsList, String clientIP) {
		log.info("Inside saveCartItems() start");
		for (CartItems cartItems : cartItemsList) {
			cartItems.setIpAddress(clientIP);
			cartItems.setCreatedDate(AppUtils.getTimestamp());
			cartItemsRepository.save(cartItems);
		}

	}

	public List<CartItems> getCartItemsList(int id) {
		List<CartItems> items=cartItemsRepository.findAllByProductId(id);
		return items;
	}
}
