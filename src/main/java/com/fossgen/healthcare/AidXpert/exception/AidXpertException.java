package com.fossgen.healthcare.AidXpert.exception;

/**
 * @author Pradeep kumar
 */
public class AidXpertException extends RuntimeException {
	private static final long serialVersionUID = 125881260791066519L;

	private String errorCode;

	private String errorMsg;

	public AidXpertException() {
	}

	public AidXpertException(String errorCode, String errorMsg) {
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
