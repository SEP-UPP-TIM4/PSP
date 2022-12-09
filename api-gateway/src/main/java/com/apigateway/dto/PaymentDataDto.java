package com.apigateway.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaymentDataDto {
    private String merchantId; //u bazi string a ovdje uuid?
    private String merchantPassword;
    private BigDecimal amount;
    //jel merchant order id i timestamp vezan za onaj moj payment request ili ce se praviti na mikroservisu za placanje pa odatle ici dalje??
    private String successUrl;
    private String failedUrl;
    private String errorUrl;

}
