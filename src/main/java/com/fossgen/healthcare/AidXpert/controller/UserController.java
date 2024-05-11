package com.fossgen.healthcare.AidXpert.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import com.fossgen.healthcare.AidXpert.Util.AppUtils;
import com.fossgen.healthcare.AidXpert.Util.GeneralUtils;
import com.fossgen.healthcare.AidXpert.Util.ImageUtils;
import com.fossgen.healthcare.AidXpert.config.CurrentUser;
import com.fossgen.healthcare.AidXpert.dto.ApiResponse;
import com.fossgen.healthcare.AidXpert.dto.LocalUser;
import com.fossgen.healthcare.AidXpert.dto.UserInfo;
import com.fossgen.healthcare.AidXpert.exception.DependentAlreadyExistException;
import com.fossgen.healthcare.AidXpert.exception.MedicalDetailsAlreadyExistException;
import com.fossgen.healthcare.AidXpert.exception.MedicalRecordsAlreadyExistException;
import com.fossgen.healthcare.AidXpert.model.Dependent;
import com.fossgen.healthcare.AidXpert.model.FileModel;
import com.fossgen.healthcare.AidXpert.model.MedicalDetails;
import com.fossgen.healthcare.AidXpert.model.MedicalRecords;
import com.fossgen.healthcare.AidXpert.model.MessageResponse;
import com.fossgen.healthcare.AidXpert.model.Orders;
import com.fossgen.healthcare.AidXpert.model.User;
import com.fossgen.healthcare.AidXpert.service.FileService;
import com.fossgen.healthcare.AidXpert.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin()
@RequestMapping("/api")
public class UserController {

	@Value("${file_upload_dir}")
	private String uploadFilesFolderPath;

	@Autowired
	private UserService userService;

	@Autowired
	FileService fileService;

	@Autowired
	PasswordEncoder encoder;

	@GetMapping("/user/me")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getCurrentUser(@CurrentUser LocalUser user) {
		return ResponseEntity.ok(GeneralUtils.buildUserInfo(user));
	}

	@GetMapping("/all")
	public ResponseEntity<?> getContent() {
		return ResponseEntity.ok("Public content goes here");
	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getUserContent() {
		return ResponseEntity.ok("User content goes here");
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAdminContent() {
		return ResponseEntity.ok("Admin content goes here");
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public ResponseEntity<?> getModeratorContent() {
		return ResponseEntity.ok("Moderator content goes here");
	}

	@PostMapping("/changePassword")
	public ResponseEntity<?> changePassword(@RequestBody UserInfo userInfo, HttpServletRequest request) {
		log.info("Inside changePassword ::" + userInfo.getEmail());
		// if (userService.checkUser(user)) {
		User userDb = userService.findUserByEmail(userInfo.getEmail());
		userDb.setPassword(encoder.encode(userInfo.getNewPassword()));
		userDb.setIpAddress(AppUtils.getClientIP(request));
		userService.updateUser(userDb);
		// }
		return ResponseEntity.ok().body(new MessageResponse("Password updated successfully!"));
	}

	@GetMapping("/getUserById/{id}")
	public User getUserById(@PathVariable("id") Long id) {
		User user = userService.findUserById(id);
		if (null != user && null != user.getImageData()) {
			try {
				user.setImageData(ImageUtils.decompressImage(user.getImageData()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return user;
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
				filePath = uploadFilesFolderPath + fileName;
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

	// @PostMapping("/updateProfile")
	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> updateProfile(@RequestPart(name = "user") User user,
			@RequestParam("file") List<MultipartFile> fileList) {
		log.info("Inside updateProfile");
		if (null != user.getDateOfBirthString() && !user.getDateOfBirthString().isBlank()) {
			user.setDateOfBirth(AppUtils.convertDateStringToDate(user.getDateOfBirthString()));
			user.setAge(AppUtils.getAge(user.getDateOfBirth()));
		}
		if (null != fileList && fileList.size() > 0) {
			for (MultipartFile file : fileList) {
				try {
					user.setImageData(ImageUtils.compressImage(file.getBytes()));
					user.setProfileImageName(file.getOriginalFilename());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		user.setIpAddress("");
		userService.updateUser(user);
		return ResponseEntity.ok().body(new MessageResponse("Profile updated successfully!"));
	}

	@PostMapping("/addDependent")
	public ResponseEntity<?> addDependent(@RequestBody Dependent dependent, HttpServletRequest request) {
		log.info("In side addDependent()");
		try {
			if (null != dependent.getDateOfBirthString() && !dependent.getDateOfBirthString().isBlank()) {
				dependent.setDateOfBirth(AppUtils.convertDateStringToDate(dependent.getDateOfBirthString()));
				dependent.setAge(AppUtils.getAge(dependent.getDateOfBirth()));
			}
			dependent.setIpAddress(AppUtils.getClientIP(request));
			dependent = userService.saveDependent(dependent);
		} catch (DependentAlreadyExistException e) {
			log.error("Exception Ocurred", e);
			return new ResponseEntity<>(new ApiResponse(false, "Dependent already exist!"), HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().body(new ApiResponse(true, "Dependent added successfully!"));
	}

	@GetMapping(path = "/dependents/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Dependent>> dependents(@RequestHeader Map<String, String> headers,
			@PathVariable("id") Long userId) {
		log.info("In side getDependents()");
		List<Dependent> dependentList = userService.getDependents(userId);
		return ResponseEntity.status(HttpStatus.OK).body(dependentList);
	}

	@PostMapping("/addMedicalDetails")
	public ResponseEntity<?> addMedicalDetails(@RequestBody MedicalDetails medicialDetails,
			HttpServletRequest request) {
		log.info("In side addMedicalDetails()");
		try {
			if (null != medicialDetails.getOrderDateString() && !medicialDetails.getOrderDateString().isBlank()) {
				medicialDetails.setOrderDate(AppUtils.convertDateStringToDate(medicialDetails.getOrderDateString()));
			}
			medicialDetails.setIpAddress(AppUtils.getClientIP(request));
			medicialDetails = userService.saveMedicalDetails(medicialDetails);
		} catch (MedicalDetailsAlreadyExistException e) {
			log.error("Exception Ocurred", e);
			return new ResponseEntity<>(new ApiResponse(false, "Medical details already exist!"),
					HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().body(new ApiResponse(true, "Medical details added successfully!"));
	}

	@GetMapping(path = "/medicalDetails/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MedicalDetails>> medicalDetails(@RequestHeader Map<String, String> headers,
			@PathVariable("id") Long userId) {
		log.info("In side medicalDetails()");
		List<MedicalDetails> medicalDetailsList = userService.getMedicalDetails(userId);
		return ResponseEntity.status(HttpStatus.OK).body(medicalDetailsList);
	}

	@DeleteMapping("/deleteMedicalDetails/{id}")
	public ResponseEntity<?> deleteMedicalDetails(@PathVariable("id") Long id) {
		userService.deleteMedicalDetails(id);
		return ResponseEntity.ok().body(new ApiResponse(true, "Medical details deleted successfully!"));
	}

	@PostMapping("/addMedicalRecords")
	public ResponseEntity<?> addMedicalRecords(@RequestBody MedicalRecords medicalRecords, HttpServletRequest request) {
		log.info("In side addMedicalRecords()");
		try {
			if (null != medicalRecords.getAttachmentFile()) {
				String filename = "";
				FileModel model = fileService.saveFile(medicalRecords.getAttachmentFile());
				log.debug("In side addMedicalRecords()" + model.getFileName());
				filename = StringUtils.cleanPath(medicalRecords.getAttachmentFile().getOriginalFilename());
				medicalRecords.setAttachment(filename);
			}
			if (null != medicalRecords.getRecordDateString() && !medicalRecords.getRecordDateString().isBlank()) {
				medicalRecords
						.setRecordDate(AppUtils.convertDateStringToTimeStamp(medicalRecords.getRecordDateString()));
			}
			medicalRecords.setIpAddress(AppUtils.getClientIP(request));
			medicalRecords = userService.saveMedicalRecords(medicalRecords);
			medicalRecords.setMrId(AppUtils.getMrId(medicalRecords.getId()));
			medicalRecords = userService.saveMedicalRecords(medicalRecords);
		} catch (MedicalRecordsAlreadyExistException e) {
			log.error("Exception Ocurred", e);
			return new ResponseEntity<>(new ApiResponse(false, "Medical Records already exist!"),
					HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().body(new ApiResponse(true, "Medical Records added successfully!"));
	}

	@GetMapping(path = "/medicalRecords/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MedicalRecords>> medicalRecords(@RequestHeader Map<String, String> headers,
			@PathVariable("id") Long userId) {
		log.info("In side medicalRecords()");
		List<MedicalRecords> medicalRecordsList = userService.getMedicalRecords(userId);
		return ResponseEntity.status(HttpStatus.OK).body(medicalRecordsList);
	}

	@DeleteMapping("/deleteMedicalRecords/{id}")
	public ResponseEntity<?> deleteMedicalRecords(@PathVariable("id") Long id) {
		userService.deleteMedicalRecords(id);
		return ResponseEntity.ok().body(new ApiResponse(true, "Medical Records deleted successfully!"));
	}

	@GetMapping(path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getUsers() {
		log.info("Inside getUsers start");
		List<User> userList = userService.getUsers();
		return ResponseEntity.status(HttpStatus.OK).body(userList);
	}

	@GetMapping(path = "/patientList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getPatientList() {
		log.info("Inside getPatientList() start");
		List<User> userList = userService.getPatientList();
		return ResponseEntity.status(HttpStatus.OK).body(userList);
	}
	@GetMapping(path = "/patientListToday", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> patientListToday() {
		log.info("Inside patientListToday() start");
		List<User> userList = userService.getTodayPatientList();
		return ResponseEntity.status(HttpStatus.OK).body(userList);
	}
	
	@GetMapping(path = "/patientList/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getPatientListById(@PathVariable("id") Long id) {
		log.info("Inside getPatientListById start");
		List<User> userList = userService.getPatientListById(id);
		return ResponseEntity.status(HttpStatus.OK).body(userList);
	}

	@GetMapping("/getPatientById/{id}")
	public User getPatientById(@PathVariable("id") Long id) {
		return userService.getPatientById(id);
	}

	@GetMapping(path = "/doctorList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getDoctorList() {
		log.info("Inside getDoctorList start");
		List<User> userList = userService.getDoctorList();
		return ResponseEntity.status(HttpStatus.OK).body(userList);
	}

	@GetMapping("/getDoctorById/{id}")
	public User getDoctorById(@PathVariable("id") Long id) {
		return userService.getDoctorById(id);
	}

	@GetMapping(path = "/ordersList/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Orders>> getOrderList(@PathVariable("id") Long id) {
		log.info("Inside getOrderList start");
		List<Orders> ordersList = userService.getOrdersList(id);
		return ResponseEntity.status(HttpStatus.OK).body(ordersList);
	}

}