package com.example.apigateway.dto;

import com.example.apigateway.model.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddCredentialsRequestDto {
    private String username;
    private String password;
    private PaymentMethod paymentMethod;
}
