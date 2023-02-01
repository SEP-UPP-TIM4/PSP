package com.psp.creditcardservice.controller;

import com.psp.creditcardservice.dto.CredentialsDto;
import com.psp.creditcardservice.dto.PaymentInfoDto;
import com.psp.creditcardservice.dto.PaymentRequestDto;
import com.psp.creditcardservice.dto.RedirectDto;
import com.psp.creditcardservice.exception.MissingCredentialsException;
import com.psp.creditcardservice.model.Payment;
import com.psp.creditcardservice.service.PaymentService;
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

    @PostMapping(value = "finish")
    public HttpStatus finish(@RequestBody PaymentInfoDto paymentInfoDto) {
        paymentService.finish(paymentInfoDto);
        log.info("Payment with id {} successfully finished", paymentInfoDto.getPaymentId());
        return HttpStatus.OK;
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
