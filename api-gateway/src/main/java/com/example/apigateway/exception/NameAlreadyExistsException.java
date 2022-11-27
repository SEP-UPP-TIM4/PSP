package com.example.apigateway.exception;

public class NameAlreadyExistsException extends RuntimeException{

    public NameAlreadyExistsException(String name) {
        super("Merchant with name: " + name + " already exists!");
    }

}
