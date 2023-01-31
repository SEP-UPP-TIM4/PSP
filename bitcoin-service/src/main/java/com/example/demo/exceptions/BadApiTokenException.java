package com.example.demo.exceptions;

public class BadApiTokenException extends RuntimeException {
    public BadApiTokenException() {
        super("Api token is invalid");
    }

    public BadApiTokenException(String message) {
        super(message);
    }
}
