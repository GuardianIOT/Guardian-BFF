package com.fiap.guardian_bff.exceptions;

public class BusinessException extends Throwable {
    private String message;

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }
}
