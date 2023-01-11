package com.psp.paypalservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CredentialsDto {

    private String merchantId;

    private String merchantPassword;

}
