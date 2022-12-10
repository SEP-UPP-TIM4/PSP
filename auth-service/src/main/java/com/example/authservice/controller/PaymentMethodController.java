package com.example.authservice.controller;

import com.example.authservice.dto.AddPaymentMethodRequestDto;
import com.example.authservice.dto.AddPaymentMethodResponseDto;
import com.example.authservice.dto.MerchantPaymentMethodsDto;
import com.example.authservice.dto.NewQRCodeDto;
import com.example.authservice.model.PaymentMethod;
import com.example.authservice.service.PaymentMethodService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/payment-method")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    private final ModelMapper modelMapper = new ModelMapper();
    private final RestTemplate restTemplate;

    public PaymentMethodController(PaymentMethodService paymentMethodService, RestTemplate restTemplate) {
        this.paymentMethodService = paymentMethodService;
        this.restTemplate = restTemplate;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(value = HttpStatus.OK)
    public AddPaymentMethodResponseDto add(@RequestBody AddPaymentMethodRequestDto request) {
        PaymentMethod paymentMethod = paymentMethodService.add(request.getName(), request.getUrl(), request.isBankPayment());
        return modelMapper.map(paymentMethod, AddPaymentMethodResponseDto.class);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<AddPaymentMethodResponseDto> getPaymentMethods() {
        return modelMapper.map(paymentMethodService.findAll(), new TypeToken<List<AddPaymentMethodResponseDto>>() {}.getType());
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePaymentMethod(@PathVariable Long id){
        paymentMethodService.delete(id);
    }

//    @GetMapping
//    @ResponseStatus(value = HttpStatus.OK)
//    public void check() {
//        String fraudCheckResponse = restTemplate.postForObject(
//                "http://QR-CODE-SERVICE/api/qr/v1/generate",
//                new NewQRCodeDto("", 0, 0), String.class
//        );
//        System.out.println(fraudCheckResponse);
//    }
}
