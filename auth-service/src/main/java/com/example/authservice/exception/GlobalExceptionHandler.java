package com.example.authservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NameAlreadyExistsException.class)
    public ResponseEntity<Object> handleNameAlreadyExistsException(NameAlreadyExistsException exception) {
        ExceptionResponse response = getExceptionResponse(exception.getMessage());
        log.warn(response.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<Object> handleUsernameAlreadyExists(UsernameAlreadyExistsException exception) {
        ExceptionResponse response = getExceptionResponse(exception.getMessage());
        log.warn(response.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleBankNotFound(NotFoundException exception) {
        ExceptionResponse response = getExceptionResponse(exception.getMessage());
        log.warn(response.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<Object> handleTokenExpired(TokenExpiredException exception) {
        ExceptionResponse response = getExceptionResponse(exception.getMessage());
        log.warn(response.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TokenNotValid.class)
    public ResponseEntity<Object> handleTokenNotValid(TokenNotValid exception) {
        ExceptionResponse response = getExceptionResponse(exception.getMessage());
        log.warn(response.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CodeNotMatchingException.class)
    public ResponseEntity<Object> handleCodeNotMatching(CodeNotMatchingException exception) {
        ExceptionResponse response = getExceptionResponse(exception.getMessage());
        log.warn(response.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException exception) {
        ExceptionResponse response = getExceptionResponse(exception.getMessage());
        log.warn(response.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    private ExceptionResponse getExceptionResponse(String message) {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage(message);
        return response;
    }
}
