package com.example.authservice.exception;

public class TokenExpiredException extends RuntimeException{
    public TokenExpiredException() {
        super("Token expired!");
    }
}
