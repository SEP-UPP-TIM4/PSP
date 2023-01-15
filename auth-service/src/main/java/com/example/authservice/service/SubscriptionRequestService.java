package com.example.authservice.service;

import com.example.authservice.dto.SubscriptionMethodInfoDTO;
import com.example.authservice.model.Credentials;
import com.example.authservice.model.Merchant;
import com.example.authservice.model.SubscriptionRequest;
import com.example.authservice.repository.SubscriptionRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SubscriptionRequestService {

    private final SubscriptionRequestRepository subscriptionRequestRepository;

    private final MerchantService merchantService;

    public SubscriptionRequestService(SubscriptionRequestRepository subscriptionRequestRepository, MerchantService merchantService) {
        this.subscriptionRequestRepository = subscriptionRequestRepository;
        this.merchantService = merchantService;
    }

    public SubscriptionMethodInfoDTO add(SubscriptionRequest subscriptionRequest){
        Merchant merchant = merchantService.findByApiKey(subscriptionRequest.getApiKey());
        for(Credentials credentials : merchant.getCredentials()){
            if(credentials.getPaymentMethod().getName().toLowerCase().trim().equals("paypal")){
                subscriptionRequestRepository.save(subscriptionRequest);
                return new SubscriptionMethodInfoDTO(credentials.getPaymentMethod().getId(), credentials.getPaymentMethod().getUrl());
            }
        }
       return null;
    }
}
