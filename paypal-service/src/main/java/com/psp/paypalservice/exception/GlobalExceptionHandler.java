package com.psp.paypalservice.exception;

import com.paypal.base.rest.PayPalRESTException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PayPalRESTException.class)
    public ResponseEntity<Object> handlePayPalException(PayPalRESTException exception) {
        ExceptionResponse response = getExceptionResponse(exception.getMessage());
        log.warn(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingCredentialsException.class)
    public ResponseEntity<Object> handleMissingCredentialsException(MissingCredentialsException exception) {
        ExceptionResponse response = getExceptionResponse(exception.getMessage());
        log.warn(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    private ExceptionResponse getExceptionResponse(String message) {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage(message);
        return response;
    }
}
