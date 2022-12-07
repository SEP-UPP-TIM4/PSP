package com.example.authservice.service;

import com.example.authservice.exception.BadCredentialsException;
import com.example.authservice.exception.NameAlreadyExistsException;
import com.example.authservice.model.Merchant;
import com.example.authservice.model.User;
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
    private final UserService userService;
    public MerchantService(MerchantRepository merchantRepository, UserService userService) {
        this.merchantRepository = merchantRepository;
        this.userService = userService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Merchant register(String name, String username, String password) {
        String apiKey = UUID.randomUUID().toString();
        String hashedApiKey = passwordEncoder.encode(apiKey);
        Optional<Merchant> merchant = merchantRepository.findByName(name);
        if(merchant.isPresent()) throw new NameAlreadyExistsException(name, Merchant.class.getSimpleName());
        User user = userService.add(username, password, "ROLE_MERCHANT");
        Merchant savedMerchant = merchantRepository.save(Merchant.builder().name(name).apiKey(hashedApiKey).user(user).build());
        return Merchant.builder().name(name).apiKey(apiKey).user(user).id(savedMerchant.getId()).build();
    }

    public Merchant findByApiKey(String apiKey){
        Optional<Merchant> merchant = merchantRepository.findAll().stream().filter(m -> passwordEncoder.matches(apiKey, m.getApiKey())).findAny();
        if(merchant.isEmpty()) throw new BadCredentialsException();
        return merchant.get();
    }
}
