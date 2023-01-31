package com.example.demo.services;

import com.example.demo.DTOs.CryptoRequestDTO;
import com.example.demo.DTOs.CryptoResponseDTO;
import com.example.demo.DTOs.PaymentRequestDTO;
import com.example.demo.exceptions.OrderNotValidException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CryptoService {

    @Autowired
    RestTemplate client;

     private final String USD_CURRENCY = "USD";

    public CryptoResponseDTO pay(PaymentRequestDTO paymentRequest, String apiToken) {


        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+apiToken);

        var request = CryptoRequestDTO.builder()
                .priceAmount(paymentRequest.getAmount())
                .priceCurrency(USD_CURRENCY)
                .receiveCurrency(USD_CURRENCY)
                .successUrl(paymentRequest.getSuccessUrl())
                .cancelUrl(paymentRequest.getFailedUrl())
                .build();

        System.out.println(request.toString());

        var entity = new HttpEntity<>(request, headers);


        var response = client.postForObject("/", entity, Map.class);

        var paymentUrl = Objects.requireNonNull(response).get("payment_url").toString();

        var id = Objects.requireNonNull(response).get("id").toString();



        if (paymentUrl == null || id == null) {
            throw new OrderNotValidException();
        }

        log.info("Payment with id " + id + " successfully created, payment URL is: " + paymentUrl);
        return new CryptoResponseDTO(paymentUrl);
    }
}
