package com.healthcare.healingxpert.exception;

public class MedicalDetailsAlreadyExistException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MedicalDetailsAlreadyExistException(String message) {
        super(message);
    }
}
