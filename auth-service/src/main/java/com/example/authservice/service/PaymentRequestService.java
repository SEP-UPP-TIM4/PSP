package com.example.authservice.service;

import com.example.authservice.dto.PaymentDataDto;
import com.example.authservice.exception.NotFoundException;
import com.example.authservice.model.Credentials;
import com.example.authservice.model.Merchant;
import com.example.authservice.model.PaymentMethod;
import com.example.authservice.model.PaymentRequest;
import com.example.authservice.repository.PaymentRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentRequestService {

    private final PaymentRequestRepository paymentRequestRepository;
    private final PaymentMethodService paymentMethodService;
    private final MerchantService merchantService;
    public PaymentRequestService(PaymentRequestRepository paymentRequestRepository, PaymentMethodService paymentMethodService, MerchantService merchantService) {
        this.paymentRequestRepository = paymentRequestRepository;
        this.paymentMethodService = paymentMethodService;
        this.merchantService = merchantService;
    }

    public PaymentRequest save(PaymentRequest paymentRequest){
        return paymentRequestRepository.save(paymentRequest);
    }

    public PaymentRequest findById(Long id){
        PaymentRequest paymentRequest = paymentRequestRepository.findById(id).orElseThrow(() -> new NotFoundException(PaymentRequest.class.getSimpleName()));;
        return paymentRequest;
    }
}
