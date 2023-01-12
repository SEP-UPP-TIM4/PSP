package com.psp.paypalservice.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {

    @Id
    @GeneratedValue
    private Long id;

    private String paymentId;

    private String merchantId;

    private String merchantSecret;

    private Double amount;

    private String currency;

    private String successUrl;

    private String errorUrl;

    private String failedUrl;
}
