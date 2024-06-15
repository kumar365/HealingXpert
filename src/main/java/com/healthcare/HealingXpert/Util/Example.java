package com.healthcare.HealingXpert.Util;

//Install the Java helper library from twilio.com/docs/java/install

import com.twilio.Twilio;
import com.twilio.rest.verify.v2.Service;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;

public class Example {
	// Find your Account SID and Auth Token at twilio.com/console
	// and set the environment variables. See http://twil.io/secure
	public static final String ACCOUNT_SID = "AC8c3a8b17b24b59a04acad7975e15c3c8";//// System.getenv("TWILIO_ACCOUNT_SID")
	public static final String AUTH_TOKEN = "3c4172b6485079718bd299cbdbd0f6a1";// System.getenv("TWILIO_AUTH_TOKEN")

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		String phoneNumber = "+919096488159";
		String email = "krvspkumar1@gmail.com";
		// generateOTP(phoneNumber);
		generateOTPToEmail(email);
		try {
			// verifyUserOTP(phoneNumber, "284337");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void generateOTP(String phoneNumber) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Service service = Service.creator("My First Verify Service").create();
		System.out.println(service.getSid());
		Verification verification = Verification.creator(service.getSid(), phoneNumber, "sms").create();
		System.out.println(verification.getStatus());
	}

	public static void verifyUserOTP(String phoneNumber, String otp) throws Exception {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Service service = Service.creator("My First Verify Service").create();
		System.out.println(service.getSid());
		try {
			VerificationCheck verificationCheck = VerificationCheck.creator(service.getSid()).setTo(phoneNumber)
					.setCode(otp).create();

			System.out.println(verificationCheck.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void generateOTPToEmail(String email) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Service service = Service.creator("My First Verify Service").create();
		System.out.println(service.getSid());
		Verification verification = Verification.creator(service.getSid(), email, "email").create();
		System.out.println(verification.getStatus());
	}

	public static void verifyUserOTPEmail(String email, String otp) throws Exception {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Service service = Service.creator("My First Verify Service").create();
		System.out.println(service.getSid());
		try {
			VerificationCheck verificationCheck = VerificationCheck.creator(service.getSid()).setTo(email).setCode(otp)
					.create();

			System.out.println(verificationCheck.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}