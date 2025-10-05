package com.healthcare.healingxpert.exception;

public class DependentAlreadyExistException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DependentAlreadyExistException(String message) {
        super(message);
    }
}
