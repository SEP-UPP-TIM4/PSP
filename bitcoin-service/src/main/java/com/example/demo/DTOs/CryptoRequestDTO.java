package com.example.demo.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CryptoRequestDTO {

    @JsonProperty("price_amount")
    private double priceAmount;

    @JsonProperty("price_currency")
    private String priceCurrency;

    @JsonProperty("receive_currency")
    private String receiveCurrency;

    @JsonProperty("success_url")
    String successUrl;

    @JsonProperty("cancel_url")
    String cancelUrl;
}
