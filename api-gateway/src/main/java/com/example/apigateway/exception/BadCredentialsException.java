package com.example.apigateway.exception;

public class BadCredentialsException extends RuntimeException{

    public BadCredentialsException() {
        super("Unauthorized access!");
    }

}