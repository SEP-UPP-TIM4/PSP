package com.example.authservice.service;

import com.example.authservice.dto.AddCredentialsRequestDto;
import com.example.authservice.dto.PaymentDataDto;
import com.example.authservice.exception.NotFoundException;
import com.example.authservice.model.*;
import com.example.authservice.repository.CredentialsRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CredentialsService {

    private final PasswordEncoder passwordEncoder;
    private final MerchantService merchantService;
    private final CredentialsRepository credentialsRepository;
    private final UserService userService;
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
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Credentials add(AddCredentialsRequestDto addCredentialsRequestDto, String username){
        Merchant merchant = merchantService.findByUserId(userService.findByUsername(username).getId());
        PaymentMethod paymentMethod = paymentMethodService.findById(addCredentialsRequestDto.getPaymentMethodId());
        Credentials credentials = Credentials.builder().username(addCredentialsRequestDto.getUsername())
                .password(passwordEncoder.encode(addCredentialsRequestDto.getPassword()))
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
    }

    public Set<Credentials> findByPaymentRequest(Long id){
        PaymentRequest paymentRequest = paymentRequestService.findById(id);
        return credentialsRepository.findByMerchantId(merchantService.findByApiKey(paymentRequest.getApiKey()).getId());

    }

    public PaymentDataDto findPaymentData(Long paymentMethodId, Long paymentRequestId){
        PaymentRequest paymentRequest = paymentRequestService.findById(paymentRequestId);
        Credentials credentialsForMethod = findByPaymentRequest(paymentRequestId).stream()
                .filter(credential -> credential.getPaymentMethod().getId().equals(paymentMethodId))
                .findFirst().orElseThrow(() -> new NotFoundException(Credentials.class.getSimpleName()));
        return new PaymentDataDto(credentialsForMethod.getUsername(), credentialsForMethod.getPassword(), paymentRequest.getAmount(),
                paymentRequest.getSuccessUrl(), paymentRequest.getFailedUrl(), paymentRequest.getErrorUrl(),
                credentialsForMethod.getPaymentMethod().getUrl());
    }

}
