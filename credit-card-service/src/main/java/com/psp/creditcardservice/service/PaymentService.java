package com.psp.creditcardservice.service;

import com.psp.creditcardservice.dto.CredentialsDto;
import com.psp.creditcardservice.dto.PaymentDataDto;
import com.psp.creditcardservice.dto.PaymentRequestDto;
import com.psp.creditcardservice.dto.ValidateDto;
import com.psp.creditcardservice.model.Currency;
import com.psp.creditcardservice.model.Payment;
import com.psp.creditcardservice.model.Transaction;
import com.psp.creditcardservice.repository.PaymentRepository;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final TransactionService transactionService;

    private final BankService bankService;

    private final RestTemplate restTemplate;

    public PaymentService(PaymentRepository paymentRepository, TransactionService transactionService, BankService bankService, RestTemplate restTemplate) {
        this.paymentRepository = paymentRepository;
        this.transactionService = transactionService;
        this.bankService = bankService;
        this.restTemplate = restTemplate;
    }

    public Payment createNewPayment(PaymentRequestDto requestDto, CredentialsDto credentialsDto) {
        Transaction transaction = Transaction.builder().amount(requestDto.getAmount()).currency(Currency.RSD).build();
        PaymentDataDto paymentDataDto = getPaymentIdAndPaymentUrl(credentialsDto, requestDto, transaction);
        return paymentRepository.save(Payment.builder().id(paymentDataDto.getPaymentId())
                .merchantId(credentialsDto.getMerchantId())
                .merchantPassword(credentialsDto.getMerchantPassword())
                .url(paymentDataDto.getPaymentUrl())
                .transaction(transactionService.save(transaction))
                .bank(bankService.getByUrl(credentialsDto.getBankUrl())).build());
    }

    private PaymentDataDto getPaymentIdAndPaymentUrl(CredentialsDto credentialsDto, PaymentRequestDto requestDto, Transaction transaction) {
        HttpEntity<ValidateDto> body = new HttpEntity<>(new ValidateDto(
               credentialsDto.getMerchantId(), credentialsDto.getMerchantPassword(),
                requestDto.getAmount(), "RSD", transaction.getId(), transaction.getTimestamp(),
                requestDto.getSuccessUrl(), requestDto.getFailedUrl(), requestDto.getErrorUrl()
        ));
        //String url = credentialsDto.getBankUrl();
        PaymentDataDto data = restTemplate.postForObject("http://localhost:9003/api/v1/payment/validate", body, PaymentDataDto.class);
        System.out.println(data.getPaymentUrl());
        System.out.println(data.getPaymentId());
        return new PaymentDataDto(data.getPaymentUrl(), data.getPaymentId());
    }
}
