package com.fossgen.healthcare.AidXpert.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fossgen.healthcare.AidXpert.Util.GeneralUtils;
import com.fossgen.healthcare.AidXpert.dto.ApiResponse;
import com.fossgen.healthcare.AidXpert.dto.JwtAuthenticationResponse;
import com.fossgen.healthcare.AidXpert.dto.LocalUser;
import com.fossgen.healthcare.AidXpert.dto.LoginRequest;
import com.fossgen.healthcare.AidXpert.dto.OtpRequest;
import com.fossgen.healthcare.AidXpert.dto.SignUpRequest;
import com.fossgen.healthcare.AidXpert.exception.UserAlreadyExistAuthenticationException;
import com.fossgen.healthcare.AidXpert.security.jwt.TokenProvider;
import com.fossgen.healthcare.AidXpert.service.OTPService;
import com.fossgen.healthcare.AidXpert.service.UserService;

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
}