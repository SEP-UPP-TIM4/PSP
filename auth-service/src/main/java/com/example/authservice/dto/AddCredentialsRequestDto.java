package com.example.authservice.dto;

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
