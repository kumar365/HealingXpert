package com.healthcare.HealingXpert.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author KUMAR
 */
public class DependentAlreadyExistException extends AuthenticationException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5570981880007077317L;

	public DependentAlreadyExistException(final String msg) {
        super(msg);
    }

}
