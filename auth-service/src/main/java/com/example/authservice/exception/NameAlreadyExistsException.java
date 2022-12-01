package com.example.authservice.exception;

public class NameAlreadyExistsException extends RuntimeException{

    public NameAlreadyExistsException(String name, String className) {
        super(className + " with name: " + name + " already exists!");
    }

}
