package com.example.authservice.service;

import com.example.authservice.exception.BadCredentialsException;
import com.example.authservice.exception.NameAlreadyExistsException;
import com.example.authservice.model.Merchant;
import com.example.authservice.repository.MerchantRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.UUID;

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
