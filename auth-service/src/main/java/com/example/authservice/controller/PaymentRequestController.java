package com.example.authservice.controller;

import com.example.authservice.dto.PaymentRequestDto;
import com.example.authservice.model.PaymentRequest;
import com.example.authservice.service.PaymentRequestService;
import org.modelmapper.ModelMapper;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@RequestMapping(value = "api/v1/payment-request")
public class PaymentRequestController {

    private final ModelMapper modelMapper = new ModelMapper();
    private final PaymentRequestService paymentRequestService;
    private final RestTemplate restTemplate;

    public PaymentRequestController(PaymentRequestService paymentRequestService, RestTemplate restTemplate) {
        this.paymentRequestService = paymentRequestService;
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public String add(@RequestBody PaymentRequestDto request, ModelMap model) throws IOException {
        PaymentRequest paymentRequest = paymentRequestService.save(modelMapper.map(request, PaymentRequest.class));
        modelMapper.map(paymentRequest, PaymentRequestDto.class);

        return "http://localhost:4200/process-payment/" + paymentRequest.getId();
    }

}
