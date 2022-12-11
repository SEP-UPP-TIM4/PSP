package com.psp.creditcardservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaymentRequestDto {

    private BigDecimal amount;

    private String successUrl;

    private String failedUrl;

    private String errorUrl;
}
