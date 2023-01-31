package com.example.demo.DTOs;


import lombok.Data;

@Data
public class PaymentRequestDTO {

    double amount;

    String successUrl;

    String failedUrl;

    String errorUrl;
}
