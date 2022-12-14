package com.psp.creditcardservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaymentResponseDto {

    private String paymentUrl;

    private Long paymentId;
}
