package com.example.authservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NameAlreadyExistsException.class)
    public ResponseEntity<Object> handleNameAlreadyExistsException(NameAlreadyExistsException exception) {
        ExceptionResponse response = getExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<Object> handleUsernameAlreadyExists(UsernameAlreadyExistsException exception) {
        ExceptionResponse response = getExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PaymentMethodNotFound.class)
    public ResponseEntity<Object> handlePaymentMethodNotFound(PaymentMethodNotFound exception) {
        ExceptionResponse response = getExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BankNotFoundException.class)
    public ResponseEntity<Object> handleBankNotFound(BankNotFoundException exception) {
        ExceptionResponse response = getExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MerchantNotFoundException.class)
    public ResponseEntity<Object> handleMerchantNotFoundExceptionException(MerchantNotFoundException exception) {
        ExceptionResponse response = getExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException exception) {
        ExceptionResponse response = getExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
    private ExceptionResponse getExceptionResponse(String message) {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage(message);
        return response;
    }
}
