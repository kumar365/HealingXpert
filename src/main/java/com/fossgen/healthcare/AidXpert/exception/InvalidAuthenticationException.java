package com.fossgen.healthcare.AidXpert.exception;

@SuppressWarnings("serial")
public class InvalidAuthenticationException extends RuntimeException {

	public InvalidAuthenticationException() {
		super("invalid email or password");
	}
}
