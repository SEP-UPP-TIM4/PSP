package com.qrcodeservice.service;

import com.qrcodeservice.dto.*;
import com.qrcodeservice.exception.NotFoundException;
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

    public void finish(PaymentInfoDto paymentInfoDto) {
        Payment payment = getById(paymentInfoDto.getPaymentId());
        payment.setAcquirerOrderId(paymentInfoDto.getAcquirerOrderId());
        payment.setAcquirerTimestamp(paymentInfoDto.getAcquirerTimestamp());
        Transaction transaction = transactionService.updateStatus(paymentInfoDto.getMerchantOrderId(), paymentInfoDto.getTransactionStatus());
        log.info("Status of transaction with is {} successfully changed to {}", transaction.getId(), transaction.getStatus());
        payment.setTransaction(transaction);
        paymentRepository.save(payment);
    }

    private Payment getById(Long paymentId) {
        return paymentRepository.findById(paymentId).orElseThrow(() -> new NotFoundException(Payment.class.getSimpleName()));
    }

}
