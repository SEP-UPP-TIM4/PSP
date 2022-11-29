package com.example.authservice.exception;

public class BadCredentialsException extends RuntimeException{

    public BadCredentialsException() {
        super("Unauthorized access!");
    }

}