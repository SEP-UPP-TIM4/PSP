package com.example.demo.exceptions;

public class MissingCredentialsException extends RuntimeException {
    public MissingCredentialsException() {
        super("Missing credentials in request header");
    }

    public MissingCredentialsException(String message) {
        super(message);
    }
}
