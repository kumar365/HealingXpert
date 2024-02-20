package com.fossgen.healthcare.AidXpert.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fossgen.healthcare.AidXpert.model.City;
import com.fossgen.healthcare.AidXpert.model.Country;
import com.fossgen.healthcare.AidXpert.model.State;
import com.fossgen.healthcare.AidXpert.service.CommonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin()
@RequestMapping("/common")
public class CommonApiController {

	@Autowired
	CommonService commonService;

	@PostMapping(path = "/generateToken", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> generateToken() {
		log.info("Generate Token Request Received and log changed");
		return ResponseEntity.status(HttpStatus.OK).body("");
	}

	@GetMapping(path = "/generatePublicKey")
	public ResponseEntity<String> generatePublicKey() {
		log.info("Get  Public Key Request Received ");
		return ResponseEntity.status(HttpStatus.OK).body("");
	}

	@GetMapping(path = "/countries", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Country>> getCountries(@RequestHeader Map<String, String> headers) {
		log.info("In side getCountries()");
		List<Country> countryList = commonService.getCountries();
		return ResponseEntity.status(HttpStatus.OK).body(countryList);
	}

	@GetMapping(path = "/states/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<State>> getStates(@RequestHeader Map<String, String> headers,
			@PathVariable("id") int countryId) {
		log.info("In side getStates()");
		List<State> stateList = commonService.getStates(countryId);
		return ResponseEntity.status(HttpStatus.OK).body(stateList);
	}

	@GetMapping(path = "/districts/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<City>> getDistricts(@RequestHeader Map<String, String> headers,
			@PathVariable("id") int stateId) {
		log.info("In side getDistricts()");
		List<City> cityList = commonService.getCities(stateId);
		return ResponseEntity.status(HttpStatus.OK).body(cityList);
	}

}
