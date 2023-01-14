package com.example.authservice.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionRequest {
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    private String apiKey;

    @Setter
    private String frequency;

    @Setter
    private BigDecimal amount;

}
