package com.psp.creditcardservice.exception;

public class MissingCredentialsException extends RuntimeException{

    public MissingCredentialsException() {
        super("Missing credentials in request header!");
    }
}
