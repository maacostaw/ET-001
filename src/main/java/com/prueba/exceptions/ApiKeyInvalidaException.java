package com.prueba.exceptions;

public class ApiKeyInvalidaException extends RuntimeException {
    public ApiKeyInvalidaException(String message) {
        super(message);
    }
}
