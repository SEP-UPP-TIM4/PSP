package com.example.authservice.service;

import com.example.authservice.dto.AddCredentialsRequestDto;
import com.example.authservice.dto.CredentialsResponseDto;
import com.example.authservice.model.*;
import com.example.authservice.repository.CredentialsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
public class CredentialsService {
    private final CredentialsRepository credentialsRepository;
    private final UserService userService;
    private final MerchantService merchantService;
    private final BankService bankService;
    private final PaymentMethodService paymentMethodService;
    private final PaymentRequestService paymentRequestService;

    public CredentialsService(MerchantService merchantService, CredentialsRepository credentialsRepository,
                              UserService userService, BankService bankService,
                              PaymentMethodService paymentMethodService,
                              PaymentRequestService paymentRequestService) {
        this.merchantService = merchantService;
        this.credentialsRepository = credentialsRepository;
        this.userService = userService;
        this.bankService = bankService;
        this.paymentMethodService = paymentMethodService;
        this.paymentRequestService = paymentRequestService;
    }

    public Credentials add(AddCredentialsRequestDto addCredentialsRequestDto, String username){
        Merchant merchant = merchantService.findByUserId(userService.findByUsername(username).getId());
        PaymentMethod paymentMethod = paymentMethodService.findById(addCredentialsRequestDto.getPaymentMethodId());
        Credentials credentials = Credentials.builder().username(addCredentialsRequestDto.getUsername())
                .password(addCredentialsRequestDto.getPassword())
                .paymentMethod(paymentMethod).build();
        if(addCredentialsRequestDto.getBankId() != null) {
            Bank bank = bankService.findById(addCredentialsRequestDto.getBankId());
            credentials.setBank(bank);
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
        log.info("Credentials successfully deleted. Credentials id: {}", id);
    }

    public Set<Credentials> findByPaymentRequest(Long id){
        PaymentRequest paymentRequest = paymentRequestService.findById(id);
        log.info("Credentials successfully gotten. Payment request id: {}", id);
        return credentialsRepository.findByMerchantId(merchantService.findByApiKey(paymentRequest.getApiKey()).getId());

    }

    public CredentialsResponseDto getCredentialsAndBankUrl(String token, Long paymentMethodId) {
        Merchant merchant = merchantService.findByApiKey(token);
        Set<Credentials> credentials = credentialsRepository.findByMerchantId(merchant.getId());
        Credentials credential = credentials.stream().filter(c -> c.getPaymentMethod().getId().equals(paymentMethodId)).findFirst().get();
        log.info("Token successfully validated. Token: {}", token);
        return new CredentialsResponseDto(credential.getUsername(), credential.getPassword(), credential.getBank().getUrl());
    }
}
