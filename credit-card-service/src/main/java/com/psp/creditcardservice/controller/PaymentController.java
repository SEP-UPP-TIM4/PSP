package com.psp.creditcardservice.controller;

import com.psp.creditcardservice.dto.PaymentRequestDto;
import com.psp.creditcardservice.dto.RedirectDto;
import com.psp.creditcardservice.model.Payment;
import com.psp.creditcardservice.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    public RedirectDto create(@RequestBody PaymentRequestDto requestDto) {
        // TODO: merchant id and merchant password get from request header
        Payment payment = paymentService.createNewPayment(requestDto);
        // TODO: make rest call to bank to get payment url and payment id
        return new RedirectDto("paymentUrl");
    }

}
