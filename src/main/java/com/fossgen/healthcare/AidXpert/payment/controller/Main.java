package com.fossgen.healthcare.AidXpert.payment.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

class Main {
	private static String ID = "AVjG17hGT7yIkBn6mpptrtX5spBBMqhZN8Okc-Qdu9b9DO1-6HOqiUk4kj9AQHB4FPMW-ZibN2GtF00T";
	private static String Secret = "EB0Q8qRj9RE4Q6Zm1zTXU0O8umeVHCVf9OvwjSJj9-AfGOsl8S1agcGWkxd8uoODc0LS2_hO32L3wmzR";
	
	private static String Authorization = "Basic "+ID+":"+Secret;
		
		
	public static void main(String[] args) throws IOException {
		URL url = new URL("https://api-m.sandbox.paypal.com/v2/payments/authorizations/0VF52814937998046");
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("GET");

		httpConn.setRequestProperty("Content-Type", "application/json");
		httpConn.setRequestProperty("Authorization", Authorization);

		InputStream responseStream = httpConn.getResponseCode() / 100 == 2
				? httpConn.getInputStream()
				: httpConn.getErrorStream();
		Scanner s = new Scanner(responseStream).useDelimiter("\\A");
		String response = s.hasNext() ? s.next() : "";
		System.out.println(response);
	}
}