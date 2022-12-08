package com.example.authservice.service;

import com.example.authservice.dto.AddCredentialsRequestDto;
import com.example.authservice.exception.MerchantNotFoundException;
import com.example.authservice.model.*;
import com.example.authservice.repository.CredentialsRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CredentialsService {

    private final PasswordEncoder passwordEncoder;
    private final MerchantService merchantService;
    private final CredentialsRepository credentialsRepository;
    private final UserService userService;
    private final BankService bankService;
    private final PaymentMethodService paymentMethodService;

    public CredentialsService(MerchantService merchantService, CredentialsRepository credentialsRepository, UserService userService, BankService bankService, PaymentMethodService paymentMethodService) {
        this.merchantService = merchantService;
        this.credentialsRepository = credentialsRepository;
        this.userService = userService;
        this.bankService = bankService;
        this.paymentMethodService = paymentMethodService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Credentials add(AddCredentialsRequestDto addCredentialsRequestDto, String username){
        Merchant merchant = merchantService.findByUserId(userService.findByUsername(username).getId());
        PaymentMethod paymentMethod = paymentMethodService.findById(addCredentialsRequestDto.getPaymentMethodId());
        Credentials credentials;
        Bank bank = bankService.findById(addCredentialsRequestDto.getBankId());
        if(bank == null) {
            credentials = Credentials.builder().username(addCredentialsRequestDto.getUsername())
                    .password(passwordEncoder.encode(addCredentialsRequestDto.getPassword()))
                    .paymentMethod(paymentMethod).build();
        }else {
            credentials = Credentials.builder().username(addCredentialsRequestDto.getUsername())
                    .password(passwordEncoder.encode(addCredentialsRequestDto.getPassword()))
                    .paymentMethod(paymentMethod).bank(bank).build();
        }
        merchant.getCredentials().add(credentials);
        return credentialsRepository.save(credentials);
    }

    public Set<Credentials> findAll(String username){
        return credentialsRepository.findByMerchantId(
                merchantService.findByUserId(userService.findByUsername(username).getId()).getId());
    }

    public void delete(Long id){
        credentialsRepository.deleteById(id);
    }

}
