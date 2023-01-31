package com.example.authservice.exception;

public class CodeNotMatchingException extends RuntimeException{
    public CodeNotMatchingException() {
        super("Code not matching!");
    }
}
