package com.psp.paypalservice.exception;

public class MissingCredentialsException extends RuntimeException{
    public MissingCredentialsException() {
        super("Missing credentials in request header!");
    }
}
