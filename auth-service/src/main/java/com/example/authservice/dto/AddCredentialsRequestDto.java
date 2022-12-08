package com.example.authservice.dto;

import com.example.authservice.model.Bank;
import com.example.authservice.model.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reactor.util.annotation.Nullable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddCredentialsRequestDto {
    private String username;
    private String password;
    private Long paymentMethodId;
    @Nullable
    private Long bankId;
}
