package com.fossgen.healthcare.AidXpert.Util;

import java.util.Random;

public class OTPGenerateUtil {
	public static String generateOTP(int length) {
		String numbers = "0123456789";
		Random rndm_method = new Random();
		char[] otp = new char[length];
		for (int i = 0; i < length; i++) {
			otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
		}
		return new String(otp);
	}

	public static void main(String[] args) {
		System.out.println(generateOTP(6));
	}
}
