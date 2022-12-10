package com.psp.creditcardservice.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {

    private Long id;    // MERCHANT_ORDER_ID

    private LocalDateTime timestamp;    // MERCHANT_TIMESTAMP

    private UUID merchantId;

    private BigDecimal amount;

    private Currency currency;
}
