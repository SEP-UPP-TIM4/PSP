package com.example.demo.errorhandling;

import com.example.demo.exceptions.BadApiTokenException;
import com.example.demo.exceptions.OrderNotFoundException;
import com.example.demo.exceptions.OrderNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
public class ClientErrorHandler implements ResponseErrorHandler {


    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is4xxClientError()) {
            if (response.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY) {
                throw new OrderNotValidException(response.getStatusText());
            }
            if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new OrderNotFoundException(response.getStatusText());
            }

            if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                throw new BadApiTokenException(response.getStatusText());
            }
        }
    }
}