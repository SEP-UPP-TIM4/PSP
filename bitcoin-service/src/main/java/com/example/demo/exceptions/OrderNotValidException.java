package com.example.demo.exceptions;

public class OrderNotValidException extends RuntimeException {
    public OrderNotValidException() {
        super();
    }

    public OrderNotValidException(String message) {
        super(message);
    }
}
