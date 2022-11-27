package com.example.apigateway.service;

import com.example.apigateway.exception.NameAlreadyExistsException;
import com.example.apigateway.model.Merchant;
import com.example.apigateway.repository.MerchantRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MerchantService {

    private final MerchantRepository merchantRepository;

    public MerchantService(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    public Merchant register(String name) {
        String apiKey = "gsadywqqgdwquewq";
        Optional<Merchant> merchant = merchantRepository.findByName(name);
        if(merchant.isPresent()) throw new NameAlreadyExistsException(name);
        return merchantRepository.save(Merchant.builder().name(name).apiKey(apiKey).build());
    }
}
