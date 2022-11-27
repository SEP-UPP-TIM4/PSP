package com.example.apigateway.dto;

import com.example.apigateway.model.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddCredentialsResponseDto {
    private String username;
    private PaymentMethod paymentMethod;
}
