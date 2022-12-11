package com.example.authservice.controller;

import com.example.authservice.dto.CredentialsResponseDto;
import com.example.authservice.service.CredentialsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final CredentialsService credentialsService;

    public AuthController(CredentialsService credentialsService) {
        this.credentialsService = credentialsService;
    }

    @PostMapping("validateToken")
    @ResponseStatus(value = HttpStatus.OK)
    public CredentialsResponseDto validate(@RequestParam("token") String token, @RequestParam("method") Long paymentMethodId) {
        return credentialsService.getCredentialsAndBankUrl(token, paymentMethodId);
    }
}
