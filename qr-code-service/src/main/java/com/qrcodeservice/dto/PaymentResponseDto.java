package com.qrcodeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaymentResponseDto {

    private String paymentUrl;

    private UUID paymentId;
}
