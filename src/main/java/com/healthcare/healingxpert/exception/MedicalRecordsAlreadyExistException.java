package com.healthcare.healingxpert.exception;

public class MedicalRecordsAlreadyExistException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MedicalRecordsAlreadyExistException(String message) {
        super(message);
    }
}
