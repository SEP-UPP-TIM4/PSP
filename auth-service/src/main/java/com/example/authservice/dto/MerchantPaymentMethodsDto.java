package com.example.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MerchantPaymentMethodsDto {
    private Long id;
    private Long paymentRequestId;
    private String name;
    private String url;
    private boolean bankPayment;
}
