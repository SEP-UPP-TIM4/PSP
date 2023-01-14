package com.psp.paypalservice.controller;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.psp.paypalservice.dto.CredentialsDto;
import com.psp.paypalservice.dto.PaymentRequestDto;
import com.psp.paypalservice.dto.RedirectDto;
import com.psp.paypalservice.dto.SubscriptionRequestDTO;
import com.psp.paypalservice.exception.MissingCredentialsException;
import com.psp.paypalservice.service.SubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping(value = "api/v1/payment/subscription")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public String create(@RequestBody SubscriptionRequestDTO requestDto, @RequestParam("paymentMethodId")Long methodId,
                       @RequestParam("planId")String planId, HttpServletRequest request) throws PayPalRESTException {
        CredentialsDto credentials = getCredentialsFromHeader(request);
        return subscriptionService.subscribe(requestDto, credentials, planId);
    }

    private CredentialsDto getCredentialsFromHeader(HttpServletRequest request) {
        String merchantId = request.getHeader("x-auth-user-id");
        String merchantPassword = request.getHeader("x-auth-user-secret");
        if(merchantId.equals("") || merchantPassword.equals("")) {
            log.warn("Missing credentials in request header! from: {}", request.getRemoteAddr());
            throw new MissingCredentialsException();
        }
        return new CredentialsDto(merchantId, merchantPassword);
    }
}
