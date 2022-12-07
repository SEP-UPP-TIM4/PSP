package com.example.authservice.exception;

public class PaymentMethodNotFound extends RuntimeException{
    public PaymentMethodNotFound() {
        super("Payment method not found");
    }
}
