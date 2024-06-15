package com.healthcare.HealingXpert.enums;

public enum ErrorConstants {

	SOMETHING_WENT_WRONG("ERR-001", "Something went wrong, Please contact admin."),
	FAIL_TO_SAVE_RECORD("ERR-003", "Failed to save records ");

	private String code;
	private String message;

	private ErrorConstants(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
