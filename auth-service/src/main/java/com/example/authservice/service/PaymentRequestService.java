package com.example.authservice.service;

import com.example.authservice.dto.PaymentDataDto;
import com.example.authservice.exception.NotFoundException;
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

    public PaymentRequest findById(Long id){
        PaymentRequest paymentRequest = paymentRequestRepository.findById(id).orElseThrow(() -> new NotFoundException(PaymentRequest.class.getSimpleName()));;
        return paymentRequest;
    }

    public PaymentDataDto getPaymentRequestForId(Long paymentRequestId) {
        PaymentRequest paymentRequest = findById(paymentRequestId);
        return new PaymentDataDto(paymentRequest.getApiKey(), paymentRequest.getAmount(),
                paymentRequest.getSuccessUrl(), paymentRequest.getFailedUrl(), paymentRequest.getErrorUrl());
    }
}
