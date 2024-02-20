package com.fossgen.healthcare.AidXpert.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author KUMAR
 */
public class UserAlreadyExistAuthenticationException extends AuthenticationException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5570981880007077317L;

	public UserAlreadyExistAuthenticationException(final String msg) {
        super(msg);
    }

}
