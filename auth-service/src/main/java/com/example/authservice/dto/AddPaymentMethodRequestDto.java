package com.example.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddPaymentMethodRequestDto {
    private String name;
    private String url;
    private boolean bankPayment;
}
