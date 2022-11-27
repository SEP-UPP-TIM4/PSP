package com.example.apigateway.service;

import com.example.apigateway.exception.BadCredentialsException;
import com.example.apigateway.exception.NameAlreadyExistsException;
import com.example.apigateway.model.Merchant;
import com.example.apigateway.repository.MerchantRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MerchantService {

    private final MerchantRepository merchantRepository;

    private final PasswordEncoder passwordEncoder;

    public MerchantService(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Merchant register(String name) {
        String apiKey = UUID.randomUUID().toString();
        String hashedApiKey = passwordEncoder.encode(apiKey);
        Optional<Merchant> merchant = merchantRepository.findByName(name);
        if(merchant.isPresent()) throw new NameAlreadyExistsException(name, Merchant.class.getSimpleName());
        Merchant savedMerchant = merchantRepository.save(Merchant.builder().name(name).apiKey(hashedApiKey).build());
        return Merchant.builder().name(name).apiKey(apiKey).id(savedMerchant.getId()).build();
    }

    public Merchant findByApiKey(String apiKey){
        Optional<Merchant> merchant = merchantRepository.findAll().stream().filter(m -> passwordEncoder.matches(apiKey, m.getApiKey())).findAny();
        if(merchant.isEmpty()) throw new BadCredentialsException();
        return merchant.get();
    }
}
