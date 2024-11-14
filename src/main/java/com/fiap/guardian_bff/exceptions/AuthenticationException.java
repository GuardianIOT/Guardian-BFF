package com.fiap.guardian_bff.exceptions;

public class AuthenticationException extends RuntimeException {
    private String message;

    public AuthenticationException(String message) {
        super(message);
        this.message = message;
    }
}