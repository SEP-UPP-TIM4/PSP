package com.example.authservice.exception;

public class TokenNotValid extends  RuntimeException{
    public TokenNotValid() {
        super("Token not valid!");
    }
}
