package com.fossgen.healthcare.AidXpert.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fossgen.healthcare.AidXpert.dto.ApiResponse;
import com.fossgen.healthcare.AidXpert.dto.UserQuestion;
import com.fossgen.healthcare.AidXpert.model.City;
import com.fossgen.healthcare.AidXpert.model.Country;
import com.fossgen.healthcare.AidXpert.model.State;
import com.fossgen.healthcare.AidXpert.model.TestDetails;
import com.fossgen.healthcare.AidXpert.model.User;
import com.fossgen.healthcare.AidXpert.service.CommonService;
import com.fossgen.healthcare.AidXpert.service.EmailServiceGAVA;
import com.fossgen.healthcare.AidXpert.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin()
@RequestMapping("/common")
public class CommonApiController {

	@Autowired
	private CommonService commonService;

	@Autowired
	private UserService userService;

	@Autowired
	private EmailServiceGAVA emailService;

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

	@PostMapping(value = "/uploadFile")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		String filePath = "";
		String fileName = "";
		try {
			if (null != file) {
				// MultipartFile file = multipartFile;
				byte[] bytes = file.getBytes();
				fileName = file.getOriginalFilename();
				System.out.println("fileName::" + fileName);
				// filePath = uploadFilesFolderPath + fileName;
				System.out.println("filePath::" + filePath);
				Path path = Paths.get(filePath);
				Files.write(path, bytes);
			} else {
				return ResponseEntity.ok().body("File updated failed!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().body("File updated successfully!");
	}

	@GetMapping(path = "/testDetails/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TestDetails> testDetails(@PathVariable("id") Integer id) {
		log.info("Inside getOrderList start");
		TestDetails testDetails = commonService.getTestDetails(id);
		return ResponseEntity.status(HttpStatus.OK).body(testDetails);
	}

	@GetMapping(path = "/doctorList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getDoctorList() {
		log.info("Inside getDoctorList start");
		List<User> userList = userService.getDoctorList();
		return ResponseEntity.status(HttpStatus.OK).body(userList);
	}

	@GetMapping("/getDoctorDetailsById/{id}")
	public User getDoctorDetailsById(@PathVariable("id") Long id) {
		return userService.getDoctorById(id);
	}

	@PostMapping("/sendUserQuestion")
	public ResponseEntity<?> sendUserQuestion(@RequestBody UserQuestion userQuestion) {
		String response = "";
		try {
			emailService.sendOtpMessage(userQuestion.getEmail(), "User question (" + userQuestion.getName() + ")",
					userQuestion.getYourQuestion());
		} catch (Exception e) {
			response = "Question sent failed";
			return ResponseEntity.ok().body(new ApiResponse(false, response));
		}
		response = "Question sent successfully";
		return ResponseEntity.ok().body(new ApiResponse(true, response));
	}

}
