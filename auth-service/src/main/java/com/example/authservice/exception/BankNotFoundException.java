package com.example.authservice.exception;

public class BankNotFoundException extends RuntimeException{
    public BankNotFoundException() {
        super("Bank not found");
    }
}
