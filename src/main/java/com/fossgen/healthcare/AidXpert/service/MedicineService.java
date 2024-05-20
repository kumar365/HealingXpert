package com.fossgen.healthcare.AidXpert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fossgen.healthcare.AidXpert.Util.AppUtils;
import com.fossgen.healthcare.AidXpert.Util.ImageUtils;
import com.fossgen.healthcare.AidXpert.model.Medicine;
import com.fossgen.healthcare.AidXpert.repository.MedicineRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class MedicineService {

	@Autowired
	MedicineRepository medicineRepository;

	public void saveMedicine(Medicine medicine) {
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
}
