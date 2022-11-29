package com.example.apigateway.controller;

import com.example.apigateway.dto.AddPaymentMethodRequestDto;
import com.example.apigateway.dto.AddPaymentMethodResponseDto;
import com.example.apigateway.dto.NewQRCodeDto;
import com.example.apigateway.model.PaymentMethod;
import com.example.apigateway.service.PaymentMethodService;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "api/v1/payment-method")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    private final ModelMapper modelMapper = new ModelMapper();

    @LoadBalanced
    RestTemplate restTemplate = new RestTemplate();

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }


    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public AddPaymentMethodResponseDto add(@RequestBody AddPaymentMethodRequestDto request) {
        PaymentMethod paymentMethod = paymentMethodService.add(request.getName(), request.getUrl());
        return modelMapper.map(paymentMethod, AddPaymentMethodResponseDto.class);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void check() {
        String fraudCheckResponse = restTemplate.postForObject(
                "http://localhost:1/api/qr/v1/generate",
                new NewQRCodeDto("", 0, 0), String.class
        );
        System.out.println(fraudCheckResponse);
    }
}
