package com.example.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddPaymentMethodResponseDto {
    private Long id;
    private String name;
    private String url;
    private boolean bankPayment;
}
