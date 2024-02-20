package com.fossgen.healthcare.AidXpert.Util;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author KUMAR
 */
@Component
public class GenerateOTP {
	private static final Logger logger = LoggerFactory.getLogger(GenerateOTP.class);

	public String otp(int len) {
		logger.info("In side otp() start::");
		String numbers = "0123456789";
		Random randomMethod = new Random();

		char[] otp = new char[len];

		for (int i = 0; i < len; i++) {
			otp[i] = numbers.charAt(randomMethod.nextInt(numbers.length()));
		}
		logger.info("In side otp() end::");
		return new String(otp);
	}
}
