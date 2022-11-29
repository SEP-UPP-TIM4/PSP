package com.example.authservice.dto;

import com.example.authservice.model.PaymentMethod;
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
