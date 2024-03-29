package com.example.authservice.controller;

import com.example.authservice.dto.AddPaymentMethodRequestDto;
import com.example.authservice.dto.AddPaymentMethodResponseDto;
import com.example.authservice.model.PaymentMethod;
import com.example.authservice.service.PaymentMethodService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "api/v1/payment-method")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    private final ModelMapper modelMapper = new ModelMapper();

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(value = HttpStatus.OK)
    public AddPaymentMethodResponseDto add(@RequestBody AddPaymentMethodRequestDto request) {
        PaymentMethod paymentMethod = paymentMethodService.add(request.getName(), request.getUrl(), request.isBankPayment());
        log.info("Payment method  successfully added. Payment method: {}", request.getName());
        return modelMapper.map(paymentMethod, AddPaymentMethodResponseDto.class);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<AddPaymentMethodResponseDto> getPaymentMethods() {
        log.info("Payment methods successfully gotten.");
        return modelMapper.map(paymentMethodService.findAll(), new TypeToken<List<AddPaymentMethodResponseDto>>() {}.getType());
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePaymentMethod(@PathVariable Long id){
        paymentMethodService.delete(id);
        log.info("Payment method successfully deleted. Payment method id: {}", id);
    }
}
