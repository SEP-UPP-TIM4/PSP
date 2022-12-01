package com.example.authservice.dto;

import com.example.authservice.model.PaymentMethod;
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
