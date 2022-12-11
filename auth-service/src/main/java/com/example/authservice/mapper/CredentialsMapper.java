package com.example.authservice.mapper;

import com.example.authservice.dto.CredentialsDto;
import com.example.authservice.dto.PaymentProcessingDto;
import com.example.authservice.model.Credentials;

public class CredentialsMapper {
    public static PaymentProcessingDto CredentialsToPaymentProcessingDto(Credentials credentials, Long id){
        return new PaymentProcessingDto(credentials.getPaymentMethod().getId(),
                credentials.getPaymentMethod().getName(), id, credentials.getPaymentMethod().getUrl());
    }

    public static CredentialsDto CredentialsToCredentialsDto(Credentials credentials){
        String bankName = "";
        if(credentials.getBank() != null) bankName = credentials.getBank().getName();
        return new CredentialsDto(credentials.getId(), credentials.getUsername(), bankName, credentials.getPaymentMethod());
    }
}
