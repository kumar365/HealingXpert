package com.healthcare.healingxpert.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.healthcare.healingxpert.util.AppUtils;
import com.healthcare.healingxpert.util.ImageUtils;
import com.healthcare.healingxpert.dto.ApiResponse;
import com.healthcare.healingxpert.model.CartItems;
import com.healthcare.healingxpert.model.Medicine;
import com.healthcare.healingxpert.model.MessageResponse;
import com.healthcare.healingxpert.service.MedicineService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin()
@RequestMapping("/medicine")
public class MedicineController {

	@Autowired
	private MedicineService medicineService;

	@GetMapping("/addMedicine")
	public List<Medicine> getMedicines() {
		log.info("Inside getMedicines start");
		return medicineService.getMedicines();
	}

	@RequestMapping(value = "/addMedicine", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> addMedicine(@RequestPart(name = "medicine") Medicine medicine,
			@RequestParam("file") List<MultipartFile> fileList) throws ParseException {
		log.info("Inside addMedicine start");
		if (null != fileList && fileList.size() > 0) {
			for (MultipartFile file : fileList) {
				try {
					medicine.setImageData(ImageUtils.compressImage(file.getBytes()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		medicine.setIpAddress("");
		medicineService.saveMedicine(medicine);
		log.info("Inside addMedicine end");
		return ResponseEntity.ok().body(new MessageResponse("Medicine added successfully!"));
	}

	@GetMapping("/getMedicineById/{id}")
	public Medicine getMedicineById(@PathVariable int id) {
		log.info("Inside getMedicineById start");
		Medicine medicine = medicineService.getMedicineById(id);
		try {
			if (null != medicine && null != medicine.getExpiryDate()) {
				medicine.setExpiryDateString(medicine.getExpiryDate().toString());
			}
			if (null != medicine && null != medicine.getImageData()) {
				medicine.setImageData(ImageUtils.decompressImage(medicine.getImageData()));
			}
		} catch (Exception e) {
			log.error("Inside getMedicineById() error::" + e);
		}
		log.debug("Inside getMedicineById id::" + id);
		return medicine;
	}

	@DeleteMapping("/deleteMedicineById/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int id) {
		log.info("Inside delete start");
		medicineService.deleteMedicineById(id);
		return new ResponseEntity<String>("Medicine is deleted successfully", HttpStatus.OK);
	}

	@PostMapping(value = "/saveCartItems")
	public ResponseEntity<?> saveCartItems(@RequestBody List<CartItems> cartItemsList, HttpServletRequest request) {
		log.info("Inside saveCartItems()");
		try {
			medicineService.saveCartItems(cartItemsList, AppUtils.getClientIP(request));
		} catch (Exception e) {
			log.error("Exception Ocurred", e);
			return new ResponseEntity<>(new ApiResponse(false, "Cart Items already exist!"), HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().body(new ApiResponse(true, "Cart Items saved successfully!"));
	}

	@GetMapping("/cartItemsList/{id}")
	public List<CartItems> cartItemsList(@PathVariable("id") int id) {
		log.info("Inside cartItemsList start");
		return medicineService.getCartItemsList(id);
	}
}
