package com.example.demo.services;

import com.example.demo.DTOs.CryptoRequestDTO;
import com.example.demo.DTOs.CryptoResponseDTO;
import com.example.demo.exceptions.OrderNotValidException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CryptoService {

    @Autowired
    RestTemplate client;

    public CryptoResponseDTO pay(CryptoRequestDTO request, String apiToken) {

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+apiToken);

        var entity = new HttpEntity<>(request, headers);


        var response = client.postForObject("/", entity, Map.class);

        var paymentUrl = Objects.requireNonNull(response).get("payment_url").toString();

        if (paymentUrl == null) {
            throw new OrderNotValidException();
        }

        return new CryptoResponseDTO(paymentUrl);
    }
}
