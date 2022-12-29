package com.qrcodeservice.exception;

import com.google.zxing.WriterException;
import com.qrcodeservice.dto.InvalidQrCodeResponseDto;
import org.modelmapper.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(WriterException.class)
    public ResponseEntity<Object> handleWriterException(WriterException exception) {
        ExceptionResponse response = getExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MissingCredentialsException.class)
    public ResponseEntity<Object> handleMissingCredentialsException(MissingCredentialsException exception) {
        ExceptionResponse response = getExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<InvalidQrCodeResponseDto> identificationCodeInvalid(final MethodArgumentNotValidException ex) {

        List list = ex.getBindingResult().getAllErrors().stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        InvalidQrCodeResponseDto dto = new InvalidQrCodeResponseDto();
        dto.setCode("500");
        for(var el : list){
            dto.getReason().add(el + "");
        }
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    private ExceptionResponse getExceptionResponse(String message) {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage(message);
        return response;
    }
}
