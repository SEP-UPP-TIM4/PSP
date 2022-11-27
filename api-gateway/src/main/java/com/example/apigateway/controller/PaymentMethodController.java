package com.example.apigateway.controller;

import com.example.apigateway.dto.AddPaymentMethodRequestDto;
import com.example.apigateway.dto.AddPaymentMethodResponseDto;
import com.example.apigateway.model.PaymentMethod;
import com.example.apigateway.service.PaymentMethodService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/payment-method")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    private final ModelMapper modelMapper = new ModelMapper();

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }


    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public AddPaymentMethodResponseDto add(@RequestBody AddPaymentMethodRequestDto request) {
        PaymentMethod paymentMethod = paymentMethodService.add(request.getName(), request.getUrl());
        return modelMapper.map(paymentMethod, AddPaymentMethodResponseDto.class);
    }
}
