package com.qrcodeservice.controller;

import com.qrcodeservice.dto.CredentialsDto;
import com.qrcodeservice.dto.PaymentRequestDto;
import com.qrcodeservice.dto.RedirectDto;
import com.qrcodeservice.exception.MissingCredentialsException;
import com.qrcodeservice.model.Payment;
import com.qrcodeservice.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping(value = "api/v1/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public RedirectDto create(@RequestBody PaymentRequestDto requestDto, @RequestParam("paymentMethodId")Long id,
                              HttpServletRequest request) {
        CredentialsDto credentials = getCredentialsFromHeader(request);
        Payment payment = paymentService.createNewPayment(requestDto, credentials);
        log.info("Payment with id {} successfully created", payment.getId());
        return new RedirectDto(payment.getUrl() + "/" + payment.getId());
    }


    private CredentialsDto getCredentialsFromHeader(HttpServletRequest request) {
        String merchantId = request.getHeader("x-auth-user-id");
        String merchantPassword = request.getHeader("x-auth-user-secret");
        String bankUrl = request.getHeader("Location");
        if(merchantId.equals("") || merchantPassword.equals("") || bankUrl.equals("") ) {
            log.warn("Missing credentials in request header! from: {}", request.getRemoteAddr());
            throw new MissingCredentialsException();
        }
        return new CredentialsDto(merchantId, merchantPassword, bankUrl);
    }
}
