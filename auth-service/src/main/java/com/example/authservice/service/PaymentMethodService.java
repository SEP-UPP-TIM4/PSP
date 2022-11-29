package com.example.authservice.service;

import com.example.authservice.exception.NameAlreadyExistsException;
import com.example.authservice.model.PaymentMethod;
import com.example.authservice.repository.PaymentMethodRepository;
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
