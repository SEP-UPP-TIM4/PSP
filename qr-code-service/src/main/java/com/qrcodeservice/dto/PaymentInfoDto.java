package com.qrcodeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentInfoDto {

    private Long merchantOrderId;

    private Long acquirerOrderId;

    private LocalDateTime acquirerTimestamp;

    private UUID paymentId;

    private String transactionStatus;

    private BigDecimal transactionAmount;

}
