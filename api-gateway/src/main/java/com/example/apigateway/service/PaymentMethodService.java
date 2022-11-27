package com.example.apigateway.service;

import com.example.apigateway.exception.NameAlreadyExistsException;
import com.example.apigateway.model.PaymentMethod;
import com.example.apigateway.repository.PaymentMethodRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public PaymentMethod add(String name, String url){
        Optional<PaymentMethod> paymentMethod = paymentMethodRepository.findByName(name);
        if(paymentMethod.isPresent()) throw new NameAlreadyExistsException(name, PaymentMethod.class.getSimpleName());
        return paymentMethodRepository.save(PaymentMethod.builder().name(name).url(url).build());
    }

}
