package com.healthcare.healingxpert.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.healthcare.healingxpert.dto.LocalUser;

public final class AppUtils {
    public static final String VERSION = "1.0";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private AppUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String getName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof LocalUser) {
            LocalUser localUser = (LocalUser) authentication.getPrincipal();
            return localUser.getUser().getDisplayName();
        }
        return "SYSTEM";
    }

    public static Timestamp getTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static String getAge(Date dateOfBirth) {
        if (dateOfBirth == null) {
            return "0 Years 0 Months";
        }
        LocalDate today = LocalDate.now();
        LocalDate birthday = dateOfBirth.toLocalDate();
        Period p = Period.between(birthday, today);
        return String.format("%d Years %d Months", p.getYears(), p.getMonths());
    }

    public static String getClientIP(HttpServletRequest request) {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || remoteAddr.isEmpty()) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }

    public static Date convertDateStringToDate(String dateString) throws ParseException {
        if (dateString == null || dateString.trim().isEmpty()) {
            return null;
        }
        java.util.Date parsed = DATE_FORMAT.parse(dateString);
        return new Date(parsed.getTime());
    }

    public static Timestamp convertDateStringToTimestamp(String dateTimeString) throws ParseException {
        if (dateTimeString == null || dateTimeString.trim().isEmpty()) {
            return null;
        }
        java.util.Date parsed = DATE_TIME_FORMAT.parse(dateTimeString);
        return new Timestamp(parsed.getTime());
    }

    public static String formatDateToString(Date date) {
        if (date == null) {
            return "";
        }
        return DATE_FORMAT.format(date);
    }

    public static String formatTimestampToString(Timestamp timestamp) {
        if (timestamp == null) {
            return "";
        }
        return DATE_TIME_FORMAT.format(timestamp);
    }

    public static String getMrId(Long id) {
        return String.format("MR%06d", id);
    }
}
