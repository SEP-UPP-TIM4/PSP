package com.example.demo.controllers;

import com.example.demo.DTOs.CryptoRequestDTO;
import com.example.demo.DTOs.CryptoResponseDTO;
import com.example.demo.exceptions.MissingCredentialsException;
import com.example.demo.services.CryptoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/crypto/payment", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class CryptoController {

    @Autowired
    private CryptoService cryptoService;


    @PostMapping
    public CryptoResponseDTO pay(@RequestBody CryptoRequestDTO cryptoRequest, @RequestParam("paymentMethodId")Long id, HttpServletRequest request) {
        String apiToken = getCredentialsFromHeader(request);
        return cryptoService.pay(cryptoRequest, apiToken);
    }

    private String getCredentialsFromHeader(HttpServletRequest request) {
        String apiToken = request.getHeader("x-auth-user-id");
        if( apiToken == null) {
            log.warn("Missing credentials in request header! from: {}", request.getRemoteAddr());
            throw new MissingCredentialsException();
        }
        if( apiToken.equals("")) {
            log.warn("Missing credentials in request header! from: {}", request.getRemoteAddr());
            throw new MissingCredentialsException();
        }
        return apiToken;
    }
}
