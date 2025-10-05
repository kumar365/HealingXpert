package com.healthcare.healingxpert.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.healingxpert.util.GeneralUtils;
import com.healthcare.healingxpert.dto.ApiResponse;
import com.healthcare.healingxpert.dto.JwtAuthenticationResponse;
import com.healthcare.healingxpert.dto.LocalUser;
import com.healthcare.healingxpert.dto.LoginRequest;
import com.healthcare.healingxpert.dto.OtpRequest;
import com.healthcare.healingxpert.dto.SignUpRequest;
import com.healthcare.healingxpert.exception.UserAlreadyExistAuthenticationException;
import com.healthcare.healingxpert.model.Ambulance;
import com.healthcare.healingxpert.model.User;
import com.healthcare.healingxpert.security.jwt.TokenProvider;
import com.healthcare.healingxpert.service.EmailServiceGAVA;
import com.healthcare.healingxpert.service.OTPService;
import com.healthcare.healingxpert.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin()
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	OTPService oTPService;

	@Autowired
	TokenProvider tokenProvider;

	@Autowired
	public EmailServiceGAVA emailService;

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		try {
			userService.registerNewUser(signUpRequest);
		} catch (UserAlreadyExistAuthenticationException e) {
			log.error("Exception Ocurred", e);
			return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"),
					HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().body(new ApiResponse(true, "User registered successfully"));
	}

	@PostMapping("/signupPatient")
	public ResponseEntity<?> registerPatient(@RequestBody SignUpRequest signUpRequest) {
		try {
			userService.registerNewPatient(signUpRequest);
		} catch (UserAlreadyExistAuthenticationException e) {
			log.error("Exception Ocurred", e);
			return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"),
					HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().body(new ApiResponse(true, "Patient registered successfully"));
	}

	@PostMapping("/signupDoctor")
	public ResponseEntity<?> registerDoctor(@RequestBody SignUpRequest signUpRequest) {
		try {
			userService.registerNewDoctor(signUpRequest);
		} catch (UserAlreadyExistAuthenticationException e) {
			log.error("Exception Ocurred", e);
			return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"),
					HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().body(new ApiResponse(true, "Doctor registered successfully"));
	}

	@PostMapping("/signupStaff")
	public ResponseEntity<?> registerStaff(@Valid @RequestBody SignUpRequest signUpRequest) {
		try {
			userService.registerNewStaff(signUpRequest);
		} catch (UserAlreadyExistAuthenticationException e) {
			log.error("Exception Ocurred", e);
			return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"),
					HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().body(new ApiResponse(true, "Doctor registered successfully"));
	}

	@PostMapping("/signupAmbulance")
	public ResponseEntity<?> registerAmbulance(@RequestBody Ambulance ambulance) {
		try {
			userService.registerAmbulance(ambulance);
		} catch (Exception e) {
			log.error("Exception Ocurred", e);
			return new ResponseEntity<>(new ApiResponse(false, "Ambulance details already in use!"),
					HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().body(new ApiResponse(true, "Ambulance registered successfully"));
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		log.info("In side authenticateUser()");
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.createToken(authentication);
		LocalUser localUser = (LocalUser) authentication.getPrincipal();
		localUser.getUser().setToken(jwt);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, GeneralUtils.buildUserInfo(localUser)));
	}

	@PostMapping("/signinMobile")
	public ResponseEntity<?> authenticateUserByMobile(@Valid @RequestBody LoginRequest loginRequest) {
		log.info("In side authenticateUserByMobile()");
		User user = userService.findUserByPhoneNumber(loginRequest.getPhoneNumber());
		Authentication authentication = null;
		if (null != user) {
			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), loginRequest.getPassword()));
		}
		LocalUser localUser = null;
		String jwt = "";
		if (null != authentication) {
			SecurityContextHolder.getContext().setAuthentication(authentication);
			jwt = tokenProvider.createToken(authentication);
			localUser = (LocalUser) authentication.getPrincipal();
		}
		if (null != localUser && null != localUser.getUser()) {
			localUser.getUser().setToken(jwt);
		}
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, GeneralUtils.buildUserInfo(localUser)));
	}

	@PostMapping("/signinAmbulance")
	public ResponseEntity<?> signinAmbulance(@RequestBody Ambulance ambulance) {
		log.info("In side signinAmbulance()");
		boolean flag = userService.checkAmbulance(ambulance);
		if (flag) {
			return ResponseEntity.ok(new ApiResponse(true, "Ambulance login successfull"));
		} else {
			return ResponseEntity.ok(new ApiResponse(false, "Ambulance login failed"));
		}
	}

	@PostMapping("/sendPhoneNumberVerificationCode")
	public ResponseEntity<?> sendPhoneNumberVerificationCode(@RequestBody OtpRequest otpRequest) {
		try {
			oTPService.sendPhoneNumberVerificationCode(otpRequest.getPhoneNumber());
		} catch (Exception e) {
			log.error("Exception Ocurred", e);
			return new ResponseEntity<>(new ApiResponse(false, "Phone Number Verification Code sent failed!"),
					HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().body(new ApiResponse(true, "Phone Number Verification Code sent successfully!"));
	}

	@PostMapping("/sendEmailVerificationCode")
	public ResponseEntity<?> sendEmailVerificationCode(@RequestBody OtpRequest otpRequest) {
		try {
			oTPService.sendEmailVerificationCode(otpRequest.getEmail());
		} catch (Exception e) {
			log.error("Exception Ocurred", e);
			return new ResponseEntity<>(new ApiResponse(false, "Email Verification Code sent failed!"),
					HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().body(new ApiResponse(true, "Email Verification Code sent successfully!"));
	}

	@PostMapping("/forgotPassword")
	public ResponseEntity<?> forgotPassword(@RequestBody OtpRequest otpRequest) {

		String token = userService.forgotPassword(otpRequest.getEmail());
		String response = "";
		if (!response.startsWith("Invalid")) {
			response = "http://localhost:4200/resetPassword?token=" + token;
			// emailService.sendOtpMessage(otpRequest.getEmail(), "Password Reset Link",
			// response);
		}
		return ResponseEntity.ok().body(new ApiResponse(true, response));
	}

	@PostMapping("/resetPassword")
	public ResponseEntity<?> resetPassword(@RequestBody User user) {
		String response = userService.resetPassword(user.getToken(), user.getPassword());
		return ResponseEntity.ok().body(new ApiResponse(true, response));
	}

	@GetMapping(path = "/getUserDataByToken/{token}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUserDataByToken(@RequestHeader Map<String, String> headers,
			@PathVariable("token") String token) {
		log.info("In side getUserDataByToken()");
		User user = userService.getUserDataByToken(token);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
}