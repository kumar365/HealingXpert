package com.healthcare.healingxpert.controller;

import java.util.List;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.healingxpert.util.AppUtils;
import com.healthcare.healingxpert.dto.ApiResponse;
import com.healthcare.healingxpert.exception.DependentAlreadyExistException;
import com.healthcare.healingxpert.model.Ambulance;
import com.healthcare.healingxpert.service.AmbulanceService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin()
@RequestMapping("/ambulance")
public class AmbulanceController {

	@Autowired
	AmbulanceService ambulanceService;

	@PostMapping(value = "/addAmbulance")
	public ResponseEntity<?> addAmbulance(@RequestBody Ambulance ambulance, HttpServletRequest request) {
		log.info("In side addAmbulance()");
		try {
			ambulance.setIpAddress(AppUtils.getClientIP(request));
			ambulance = ambulanceService.saveAmbulance(ambulance);
		} catch (DependentAlreadyExistException e) {
			log.error("Exception Ocurred", e);
			return new ResponseEntity<>(new ApiResponse(false, "Ambulance already exist!"), HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().body(new ApiResponse(true, "Ambulance details added successfully!"));
	}

	@GetMapping(path = "/ambulances/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Ambulance>> ambulances(@RequestHeader Map<String, String> headers,
			@PathVariable("id") Long id) {
		log.info("In side getAmbulances()");
		List<Ambulance> ambulanceList = ambulanceService.getAmbulances();
		return ResponseEntity.status(HttpStatus.OK).body(ambulanceList);
	}
}