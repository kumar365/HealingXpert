package com.healthcare.healingxpert.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateUtils {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private DateUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String yearDifference(String fromYear, String toYear) {
        try {
            java.util.Date from = DATE_FORMAT.parse(fromYear);
            java.util.Date to = DATE_FORMAT.parse(toYear);
            LocalDateTime fromDate = new Timestamp(from.getTime()).toLocalDateTime();
            LocalDateTime toDate = new Timestamp(to.getTime()).toLocalDateTime();
            long years = ChronoUnit.YEARS.between(fromDate, toDate);
            return years + " Years";
        } catch (ParseException e) {
            return "0 Years";
        }
    }

    public static Date getSqlDate() {
        return new Date(System.currentTimeMillis());
    }

    public static Timestamp getSqlTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
