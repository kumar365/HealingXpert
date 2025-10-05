package com.healthcare.healingxpert.exception;

public class HealingXpertException extends RuntimeException {
    private String errorMsg;

    public HealingXpertException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
