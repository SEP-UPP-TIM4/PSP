package com.example.authservice.controller;

import com.example.authservice.dto.PaymentDataDto;
import com.example.authservice.dto.PaymentRequestDto;
import com.example.authservice.model.PaymentRequest;
import com.example.authservice.service.PaymentRequestService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(value = "/{paymentRequestId}")
    @ResponseStatus(value = HttpStatus.OK)
    public PaymentDataDto getProcessPaymentData(@PathVariable Long paymentRequestId){
        return paymentRequestService.getPaymentRequestForId(paymentRequestId);
    }

}
