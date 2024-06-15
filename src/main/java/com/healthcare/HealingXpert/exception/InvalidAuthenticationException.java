package com.healthcare.HealingXpert.exception;

@SuppressWarnings("serial")
public class InvalidAuthenticationException extends RuntimeException {

	public InvalidAuthenticationException() {
		super("invalid email or password");
	}
}
