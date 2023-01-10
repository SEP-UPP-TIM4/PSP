package com.psp.paypalservice.controller;

import com.psp.paypalservice.dto.PaymentRequestDto;
import com.psp.paypalservice.dto.RedirectDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping(value = "api/v1/payment")
public class PaymentController {

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public RedirectDto create(@RequestBody PaymentRequestDto requestDto, @RequestParam("paymentMethodId")Long id,
                              HttpServletRequest request) {
        return new RedirectDto("Check one,two,three");
    }
}
