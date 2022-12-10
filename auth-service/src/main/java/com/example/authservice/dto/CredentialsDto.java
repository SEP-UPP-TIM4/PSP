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
public class CredentialsDto {
    private Long id;
    private String username;
    private String bankName;
    private PaymentMethod paymentMethod;
}
