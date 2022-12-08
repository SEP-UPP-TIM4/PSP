package com.example.authservice.service;

import com.example.authservice.model.PaymentRequest;
import com.example.authservice.repository.PaymentRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentRequestService {

    private final PaymentRequestRepository paymentRequestRepository;

    public PaymentRequestService(PaymentRequestRepository paymentRequestRepository) {
        this.paymentRequestRepository = paymentRequestRepository;
    }

    public PaymentRequest save(PaymentRequest paymentRequest){
        return paymentRequestRepository.save(paymentRequest);
    }

}