package com.example.authservice.exception;

public class MerchantNotFoundException extends RuntimeException{
    public MerchantNotFoundException() {
        super("Merchant not found");
    }
}
