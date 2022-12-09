package com.example.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaymentDataDto {
    private String merchantId; //u bazi string a ovdje uuid?
    private String merchantPassword;
    private BigDecimal amount;
    private String successUrl;
    private String failedUrl;
    private String errorUrl;
    private String url;
}
