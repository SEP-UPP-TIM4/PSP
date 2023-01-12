package com.psp.paypalservice.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.psp.paypalservice.dto.CredentialsDto;
import com.psp.paypalservice.dto.PaymentRequestDto;
import com.psp.paypalservice.dto.RedirectDto;
import com.psp.paypalservice.exception.MissingCredentialsException;
import com.psp.paypalservice.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

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
                              HttpServletRequest request) throws PayPalRESTException {
        CredentialsDto credentials = getCredentialsFromHeader(request);
        Payment payment = paymentService.createPayment(requestDto, credentials);
        log.info("Payment request successfully created. Payment id: {}", payment.getId());
        return new RedirectDto(getPaymentLink(payment));
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

    private String getPaymentLink(Payment payment){
        for(Links link: payment.getLinks()){
            if(link.getRel().equals("approval_url")){
                return link.getHref();
            }
        }
        return null;
    }

    @GetMapping(value = "/execute")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<HttpStatus> execute(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) throws PayPalRESTException {
        String retUrl = paymentService.executePayment(paymentId, payerId);
        return ResponseEntity.status(HttpStatus.SEE_OTHER).location(URI.create(retUrl)).build();
    }
}
