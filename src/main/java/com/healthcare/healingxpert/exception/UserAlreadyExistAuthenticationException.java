package com.healthcare.healingxpert.exception;

import org.springframework.security.core.AuthenticationException;

public class UserAlreadyExistAuthenticationException extends AuthenticationException {
    private static final long serialVersionUID = 5570981880007077317L;

    public UserAlreadyExistAuthenticationException(String msg) {
        super(msg);
    }
}
