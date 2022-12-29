package com.qrcodeservice.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                        // MERCHANT_ORDER_ID

    @Setter
    private LocalDateTime timestamp;        // MERCHANT_TIMESTAMP

    @Setter
    private BigDecimal amount;

    @Setter
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Setter
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private TransactionStatus status = TransactionStatus.PENDING;

    @PrePersist
    protected void onCreate() {
        timestamp = LocalDateTime.now();
    }

}
