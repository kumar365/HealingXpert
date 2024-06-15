package com.healthcare.HealingXpert.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author KUMAR
 */
public class MedicalRecordsAlreadyExistException extends AuthenticationException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5570981880007077317L;

	public MedicalRecordsAlreadyExistException(final String msg) {
        super(msg);
    }

}
