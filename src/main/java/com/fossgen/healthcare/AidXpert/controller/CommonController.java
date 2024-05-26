package com.fossgen.healthcare.AidXpert.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fossgen.healthcare.AidXpert.Util.FileUtils;
import com.fossgen.healthcare.AidXpert.Util.ImageUtils;
import com.fossgen.healthcare.AidXpert.dto.ApiResponse;
import com.fossgen.healthcare.AidXpert.dto.Staffing;
import com.fossgen.healthcare.AidXpert.dto.UserQuestion;
import com.fossgen.healthcare.AidXpert.model.Ambulance;
import com.fossgen.healthcare.AidXpert.model.City;
import com.fossgen.healthcare.AidXpert.model.Country;
import com.fossgen.healthcare.AidXpert.model.State;
import com.fossgen.healthcare.AidXpert.model.TestDetails;
import com.fossgen.healthcare.AidXpert.model.User;
import com.fossgen.healthcare.AidXpert.service.AmbulanceService;
import com.fossgen.healthcare.AidXpert.service.CommonService;
import com.fossgen.healthcare.AidXpert.service.EmailServiceGAVA;
import com.fossgen.healthcare.AidXpert.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin()
@RequestMapping("/common")
public class CommonController {

	@Autowired
	private CommonService commonService;

	@Autowired
	private UserService userService;

	@Autowired
	AmbulanceService ambulanceService;

	@Autowired
	private EmailServiceGAVA emailService;

	@Value("${file_upload_dir}")
	private String fileUploadDir;

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

	@GetMapping(path = "/states", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<State>> getStates(@RequestHeader Map<String, String> headers) {
		log.info("In side getStates()");
		List<State> stateList = commonService.getStates();
		return ResponseEntity.status(HttpStatus.OK).body(stateList);
	}

	@GetMapping(path = "/states/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<State>> getStatesById(@RequestHeader Map<String, String> headers,
			@PathVariable("id") int countryId) {
		log.info("In side getStatesById()");
		List<State> stateList = commonService.getStates(countryId);
		return ResponseEntity.status(HttpStatus.OK).body(stateList);
	}
	
	@GetMapping(path = "/districts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<City>> getDistricts(@RequestHeader Map<String, String> headers) {
		log.info("In side getDistricts()");
		List<City> cityList = commonService.getCities();
		return ResponseEntity.status(HttpStatus.OK).body(cityList);
	}

	@GetMapping(path = "/districts/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<City>> getDistrictsById(@RequestHeader Map<String, String> headers,
			@PathVariable("id") int stateId) {
		log.info("In side getDistrictsById()");
		List<City> cityList = commonService.getCities(stateId);
		return ResponseEntity.status(HttpStatus.OK).body(cityList);
	}

	@GetMapping(path = "/testDetailsList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TestDetails>> testDetailsList() {
		log.info("Inside testDetailsList start");
		List<TestDetails> testDetailsList = commonService.getTestDetailsList();
		return ResponseEntity.status(HttpStatus.OK).body(testDetailsList);
	}

	@GetMapping(path = "/testDetails/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TestDetails> testDetails(@PathVariable("id") Integer id) {
		log.info("Inside testDetails start");
		TestDetails testDetails = commonService.getTestDetails(id);
		log.info("Inside testDetails end");
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
		User user = userService.getDoctorById(id);
		if (null != user && null != user.getImageData()) {
			try {
				user.setImageData(ImageUtils.decompressImage(user.getImageData()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return user;
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

	@PostMapping(value = "/fileUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> fileUpload(@RequestParam("file") MultipartFile file) {
		String message = "";
		String filePath = "";
		String fileName = "";
		try {
			if (null != file) {
				fileName = file.getOriginalFilename();
				FileUtils.createDirectory(fileUploadDir);
				filePath = fileUploadDir + fileName;
				FileUtils.writeFile(filePath, file.getBytes());
				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseEntity<Object>(message, HttpStatus.OK));
			} else {
				message = "Could not upload the file: ";
				return ResponseEntity.ok().body(message);
			}
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseEntity<Object>(message, HttpStatus.OK));
		}
	}

	@PostMapping(value = "/prescriptionUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> prescriptionUpload(@RequestParam("file") MultipartFile file) {
		String message = "";
		String filePath = "";
		String fileName = "";
		String fileDir = "";
		try {
			if (null != file) {
				fileName = file.getOriginalFilename();
				fileDir = fileUploadDir + "/Prescriptions/";
				FileUtils.createDirectory(fileDir);
				filePath = fileDir + fileName;
				FileUtils.writeFile(filePath, file.getBytes());
				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseEntity<Object>(message, HttpStatus.OK));
			} else {
				message = "Could not upload the file: ";
				return ResponseEntity.ok().body(message);
			}
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseEntity<Object>(message, HttpStatus.OK));
		}
	}

	@GetMapping(path = "/getAmbulances", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Ambulance>> getAmbulances(@RequestHeader Map<String, String> headers) {
		log.info("In side getAmbulances()");
		List<Ambulance> ambulanceList = ambulanceService.getAmbulances();
		return ResponseEntity.status(HttpStatus.OK).body(ambulanceList);
	}

	@PostMapping("/sendStaffingData")
	public ResponseEntity<?> sendStaffingData(@RequestBody Staffing staffing) {
		String response = "";
		try {
//			emailService.sendOtpMessage(staffing.getEmail(), "Staffing needs (" + staffing.getStaffingNeeds() + ")",
//					staffing.getHospitalName());
		} catch (Exception e) {
			response = "Staffing needst failed";
			return ResponseEntity.ok().body(new ApiResponse(false, response));
		}
		response = "Staffing needs sent successfully";
		return ResponseEntity.ok().body(new ApiResponse(true, response));
	}

	@RequestMapping(value = "/sendStaffingDataWithFile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> sendStaffingDataWithFile(@RequestPart(name = "staffing") Staffing staffing,
			@RequestParam("file") List<MultipartFile> fileList) {
		String response = "";
		String filePath = "";
		String fileName = "";
		String fileDir = "";
		try {
			if (null != fileList && fileList.size() > 0) {
				for (MultipartFile file : fileList) {
					fileName = file.getOriginalFilename();
					fileDir = fileUploadDir + "/StaffingData/";
					FileUtils.createDirectory(fileDir);
					filePath = fileDir + fileName;
					FileUtils.writeFile(filePath, file.getBytes());
				}
				// response = "Staffing needs related file uploaded successfully: " + fileName;
			} else {
				response = "Staffing needs related file uploaded failed";
				return ResponseEntity.ok().body(new ApiResponse(false, response));
			}

//			emailService.sendOtpMessage(staffing.getEmail(), "Staffing needs (" + staffing.getStaffingNeeds() + ")",
//					staffing.getHospitalName());
		} catch (Exception e) {
			response = "Staffing needs sent failed";
			return ResponseEntity.ok().body(new ApiResponse(false, response));
		}
		response = "Staffing needs sent successfully";
		return ResponseEntity.ok().body(new ApiResponse(true, response));
	}
}
