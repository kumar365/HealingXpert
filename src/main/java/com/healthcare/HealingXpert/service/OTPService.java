package com.healthcare.HealingXpert.service;

public interface OTPService {

	public void sendPhoneNumberVerificationCode(String phoneNumber) throws Exception;

	public void sendEmailVerificationCode(String email) throws Exception;

	boolean validateOTP(String phoneNumber, String otp);
}
