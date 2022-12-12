package com.psp.creditcardservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaymentDataDto {

    private String paymentUrl;

    private Long paymentId;
}
