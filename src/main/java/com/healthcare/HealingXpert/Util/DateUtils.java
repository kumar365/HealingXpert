package com.healthcare.HealingXpert.Util;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.stereotype.Component;

/**
 * @author KUMAR
 */
@Component
public class DateUtils {
	public static Date getSqlDate() {
		Date sqlDate = new Date(System.currentTimeMillis());
		return sqlDate;
	}

	public static Timestamp getSqlTimestamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp;
	}
}
