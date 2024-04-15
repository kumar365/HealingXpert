package com.fossgen.healthcare.AidXpert.Util;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author KUMAR
 */
@Component
public class AppUtil {

	public static final String VERSION = "0.0.1";

	public static Timestamp getTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("X-FORWARDED-FOR");
		if (ip == null || ip.isEmpty()) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String getName() {
		String name = "";
		if (null != SecurityContextHolder.getContext()
				&& null != SecurityContextHolder.getContext().getAuthentication()) {
			name = SecurityContextHolder.getContext().getAuthentication().getName();
		}
		return name;
	}

	public static String getMrId(Long id) {
		String mrId = "MR-";
		String idString = String.valueOf(id);
		if (idString.length() > 0) {
			if (idString.length() == 1) {
				mrId = mrId + "000" + idString;
			} else if (idString.length() == 2) {
				mrId = mrId + "00" + idString;
			} else if (idString.length() == 3) {
				mrId = mrId + "0" + idString;
			}
		}
		return mrId;
	}

	public static Timestamp convertDateStringToTimeStamp(String recordDateString) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDate = null;
		Timestamp timestamp = null;
		try {
			parsedDate = dateFormat.parse(recordDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		timestamp = new java.sql.Timestamp(parsedDate.getTime());
		return timestamp;
	}

	public static java.sql.Date convertDateStringToDate(String dateString) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDate = null;
		java.sql.Date sqlDate= null;
		try {
			parsedDate = dateFormat.parse(dateString);
			 sqlDate = new java.sql.Date(parsedDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sqlDate;
	}
	
	public static String getAge(java.sql.Date dateOfBirth) {
		String age = "";
		LocalDate today = LocalDate.now();
		LocalDate birthday = dateOfBirth.toLocalDate();
		Period p = Period.between(birthday, today);
		age = String.valueOf(p.getYears()) + " Years " + p.getMonths() + " Months";
		return age;
	}
}

