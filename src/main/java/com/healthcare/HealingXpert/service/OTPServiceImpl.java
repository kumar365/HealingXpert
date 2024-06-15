package com.healthcare.HealingXpert.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.HealingXpert.dto.EmailDTO;

@Service
public class OTPServiceImpl implements OTPService {

	@Autowired
	OtpGenerator OtpGenerator;

	@Autowired
	EmailService emailService;

	@Override
	public void sendPhoneNumberVerificationCode(String phoneNumber) throws Exception {
		String otp = generateOTP(phoneNumber);
		System.out.println(otp);
	}

	@Override
	public void sendEmailVerificationCode(String email) throws Exception {
		String otp = generateOTP(email);
		List<String> recipients= new ArrayList<String>();
		recipients.add(email);
		EmailDTO emailDTO = new EmailDTO();
		emailDTO.setAttachmentPath("");
		emailDTO.setBccList(null);
		emailDTO.setBody(otp);
		emailDTO.setCcList(null);
		emailDTO.setHtml(true);
		emailDTO.setRecipients(recipients);
		emailDTO.setSubject("OTP to verify");
		emailService.sendSimpleMessage(emailDTO);
	}

	private String generateOTP(String key) {
		Integer otp;
		otp = OtpGenerator.generateOTP(key);
		return String.valueOf(otp);
	}

	@Override
	public boolean validateOTP(String phoneNumber, String otp) {
		// Call the third-party OTP provider to validate the OTP and return true if
		// valid, false otherwise
		return false;

	}
}
