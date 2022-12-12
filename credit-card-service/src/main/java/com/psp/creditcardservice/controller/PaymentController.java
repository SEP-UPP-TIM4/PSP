package com.psp.creditcardservice.controller;

import com.psp.creditcardservice.dto.CredentialsDto;
import com.psp.creditcardservice.dto.PaymentRequestDto;
import com.psp.creditcardservice.dto.RedirectDto;
import com.psp.creditcardservice.model.Payment;
import com.psp.creditcardservice.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "api/v1/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public RedirectDto create(@RequestBody PaymentRequestDto requestDto, @RequestParam("paymentMethodId")Long id,
                              HttpServletRequest request) {
        String merchantId = request.getHeader("x-auth-user-id");
        String merchantPassword = request.getHeader("x-auth-user-secret");
        String bankUrl = request.getHeader("Location");
        Payment payment = paymentService.createNewPayment(requestDto, new CredentialsDto(merchantId, merchantPassword, bankUrl));
        return new RedirectDto(payment.getUrl() + payment.getId());
    }

}
