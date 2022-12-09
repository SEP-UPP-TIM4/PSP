package com.example.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentProcessingDto {
    private Long paymentMethodId;
    private String paymentName;
    private Long paymentRequestId;
}
