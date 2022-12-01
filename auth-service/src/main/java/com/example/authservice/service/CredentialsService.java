package com.example.authservice.service;

import com.example.authservice.dto.AddCredentialsRequestDto;
import com.example.authservice.model.Credentials;
import com.example.authservice.model.Merchant;
import com.example.authservice.repository.CredentialsRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CredentialsService {

    private final PasswordEncoder passwordEncoder;
    private final MerchantService merchantService;
    private final CredentialsRepository credentialsRepository;

    public CredentialsService(MerchantService merchantService, CredentialsRepository credentialsRepository) {
        this.merchantService = merchantService;
        this.credentialsRepository = credentialsRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Credentials add(AddCredentialsRequestDto addCredentialsRequestDto, String apiKey){
        Merchant merchant = merchantService.findByApiKey(apiKey);
        Credentials credentials = Credentials.builder().username(addCredentialsRequestDto.getUsername())
                .password(passwordEncoder.encode(addCredentialsRequestDto.getPassword()))
                .paymentMethod(addCredentialsRequestDto.getPaymentMethod()).build();
        merchant.getCredentials().add(credentials);
        return credentialsRepository.save(credentials);
    }

}
