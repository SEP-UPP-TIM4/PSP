package com.example.authservice.controller;

import com.example.authservice.dto.PaymentDataDto;
import com.example.authservice.dto.PaymentRequestDto;
import com.example.authservice.model.PaymentRequest;
import com.example.authservice.service.PaymentRequestService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Slf4j
@RequestMapping(value = "api/v1/payment-request")
public class PaymentRequestController {

    private final ModelMapper modelMapper = new ModelMapper();
    private final PaymentRequestService paymentRequestService;

    public PaymentRequestController(PaymentRequestService paymentRequestService) {
        this.paymentRequestService = paymentRequestService;
    }

    @PostMapping
    public String add(@RequestBody PaymentRequestDto request, ModelMap model) throws IOException {
        PaymentRequest paymentRequest = paymentRequestService.save(modelMapper.map(request, PaymentRequest.class));
        modelMapper.map(paymentRequest, PaymentRequestDto.class);
        log.info("Payment request successfully added. Api key: {}", request.getApiKey());
        return "https://localhost:4200/process-payment/" + paymentRequest.getId();
    }

    @GetMapping(value = "/{paymentRequestId}")
    @ResponseStatus(value = HttpStatus.OK)
    public PaymentDataDto getProcessPaymentData(@PathVariable Long paymentRequestId){
        return paymentRequestService.getPaymentRequestForId(paymentRequestId);
    }

}
