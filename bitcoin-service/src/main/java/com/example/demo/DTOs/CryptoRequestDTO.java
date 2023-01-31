package com.example.demo.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CryptoRequestDTO(@JsonProperty("price_amount") double priceAmount,
                               @JsonProperty("price_currency") String priceCurrency,
                               @JsonProperty("receive_currency") String receiveCurrency,
                               @JsonProperty("order_id") String orderId, String title,
                               String description, @JsonProperty("success_url") String successUrl,
                               @JsonProperty("cancel_url") String cancelUrl) {
}
