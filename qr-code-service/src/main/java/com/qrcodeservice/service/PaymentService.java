package com.qrcodeservice.service;

import com.qrcodeservice.dto.CredentialsDto;
import com.qrcodeservice.dto.PaymentRequestDto;
import com.qrcodeservice.dto.PaymentResponseDto;
import com.qrcodeservice.dto.ValidateDto;
import com.qrcodeservice.model.Currency;
import com.qrcodeservice.model.Payment;
import com.qrcodeservice.model.Transaction;
import com.qrcodeservice.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class PaymentService {
    private final TransactionService transactionService;
    private final PaymentRepository paymentRepository;
    private final BankService bankService;
    private final RestTemplate restTemplate;
    public PaymentService(TransactionService transactionService, PaymentRepository paymentRepository, BankService bankService, RestTemplate restTemplate) {
        this.transactionService = transactionService;
        this.paymentRepository = paymentRepository;
        this.bankService = bankService;
        this.restTemplate = restTemplate;
    }

    public Payment createNewPayment(PaymentRequestDto requestDto, CredentialsDto credentialsDto) {
        Transaction transaction = Transaction.builder().amount(requestDto.getAmount()).currency(Currency.RSD).build();
        Transaction savedTransaction = transactionService.save(transaction);
        log.info("Transaction with id {} successfully created", savedTransaction.getId());
        PaymentResponseDto paymentDataDto = getPaymentIdAndPaymentUrl(credentialsDto, requestDto, transaction);
        return paymentRepository.save(Payment.builder().id(paymentDataDto.getPaymentId())
                .merchantId(credentialsDto.getMerchantId())
                .url(paymentDataDto.getPaymentUrl())
                .transaction(savedTransaction)
                .bank(bankService.getByUrl(credentialsDto.getBankUrl())).build());
    }

    private PaymentResponseDto getPaymentIdAndPaymentUrl(CredentialsDto credentialsDto, PaymentRequestDto requestDto, Transaction transaction) {
        HttpEntity<ValidateDto> body = new HttpEntity<>(new ValidateDto(
                credentialsDto.getMerchantId(), credentialsDto.getMerchantPassword(),
                requestDto.getAmount(), "RSD", transaction.getId(), transaction.getTimestamp(),
                requestDto.getSuccessUrl(), requestDto.getFailedUrl(), requestDto.getErrorUrl()
        ));
        PaymentResponseDto data = new PaymentResponseDto();
        try {
            data = restTemplate.postForObject("http://localhost:9003/api/v1/payment/validate-qr", body, PaymentResponseDto.class);
        } catch (Exception ex) {
            log.error("Error in communication");
        }
        return new PaymentResponseDto(data.getPaymentUrl(), data.getPaymentId());
    }

}
