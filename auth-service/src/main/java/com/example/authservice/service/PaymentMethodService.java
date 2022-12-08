package com.example.authservice.service;

import com.example.authservice.exception.NameAlreadyExistsException;
import com.example.authservice.exception.PaymentMethodNotFound;
import com.example.authservice.model.PaymentMethod;
import com.example.authservice.repository.PaymentMethodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public PaymentMethod add(String name, String url, boolean bankPayment){
        Optional<PaymentMethod> paymentMethod = paymentMethodRepository.findByName(name);
        if(paymentMethod.isPresent()) throw new NameAlreadyExistsException(name, PaymentMethod.class.getSimpleName());
        return paymentMethodRepository.save(PaymentMethod.builder().name(name).url(url).bankPayment(bankPayment).build());
    }

    public List<PaymentMethod> findAll(){
        return paymentMethodRepository.findAll();
    }

    public PaymentMethod findById(Long id){
        Optional<PaymentMethod> paymentMethod = paymentMethodRepository.findById(id);
        if(paymentMethod.isEmpty()) throw new PaymentMethodNotFound();
        return paymentMethod.get();
    }

    public void delete(Long id){
        if(paymentMethodRepository.findById(id) != null)
            paymentMethodRepository.deleteById(id);
        else
            throw new PaymentMethodNotFound();
    }
}
