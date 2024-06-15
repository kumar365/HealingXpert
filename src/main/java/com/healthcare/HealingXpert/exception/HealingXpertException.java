package com.healthcare.HealingXpert.exception;

/**
 * @author Pradeep kumar
 */
public class HealingXpertException extends RuntimeException {
	private static final long serialVersionUID = 125881260791066519L;

	private String errorCode;

	private String errorMsg;

	public HealingXpertException() {
	}

	public HealingXpertException(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
}
