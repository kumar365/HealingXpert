package com.healthcare.healingxpert.util;

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

	public String otp(int length) {
		logger.info("In side otp() start::");
		String numbers = "0123456789";
		Random random = new Random();

		char[] otp = new char[length];

		for (int i = 0; i < length; i++) {
			otp[i] = numbers.charAt(random.nextInt(numbers.length()));
		}
		logger.info("In side otp() end::");
		return new String(otp);
	}
}
