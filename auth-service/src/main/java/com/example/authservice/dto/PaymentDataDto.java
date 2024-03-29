package com.example.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PaymentDataDto {

    private String apiKey;

    private BigDecimal amount;

    private String successUrl;

    private String failedUrl;

    private String errorUrl;
}
