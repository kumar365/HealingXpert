package com.fossgen.healthcare.AidXpert.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fossgen.healthcare.AidXpert.Util.AppUtils;
import com.fossgen.healthcare.AidXpert.model.Madicine;
import com.fossgen.healthcare.AidXpert.service.MedicineService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin()
@RequestMapping("/medicine")
public class MedicineController {

	@Autowired
	private MedicineService medicineService;

	@GetMapping("/addMedicine")
	public List<Madicine> getMedicines() {
		log.info("Inside getMedicines start");
		return medicineService.getMedicines();
	}

	@PostMapping("/addMedicine")
	void addMedicine(@RequestBody Madicine madicine, HttpServletRequest request) {
		log.info("Inside addMedicine start");
		madicine.setIpAddress(AppUtils.getClientIP(request));
		medicineService.createMedicine(madicine);
		log.info("Inside addMedicine end");
	}

	@GetMapping("/getMedicineById")
	public Madicine getMedicineById(@RequestParam int id) {
		log.info("Inside getMedicineById start");
		log.debug("Inside getMedicineById id::" + id);
		return medicineService.getMedicineById(id);
	}

	@DeleteMapping("/deleteMedicineById/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int id) {
		log.info("Inside delete start");
		medicineService.deleteMedicineById(id);
		return new ResponseEntity<String>("Medicine is deleted successfully", HttpStatus.OK);
	}
}
