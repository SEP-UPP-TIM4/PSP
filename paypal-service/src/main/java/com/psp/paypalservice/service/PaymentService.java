package com.psp.paypalservice.service;

import com.paypal.api.payments.Transaction;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.psp.paypalservice.dto.CredentialsDto;
import com.psp.paypalservice.dto.PaymentRequestDto;
import com.psp.paypalservice.model.PaymentRequest;
import com.psp.paypalservice.repository.PaymentRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.paypal.api.payments.PaymentExecution;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PaymentService {

    private final PaymentRequestRepository paymentRequestRepository;

    private final TransactionService transactionService;
    public PaymentService(PaymentRequestRepository paymentRequestRepository, TransactionService transactionService) {
        this.paymentRequestRepository = paymentRequestRepository;
        this.transactionService = transactionService;
    }

    public Payment createPayment(PaymentRequestDto paymentRequestDto, CredentialsDto credentials) throws PayPalRESTException {
        Payment payment = new Payment();

        payment.setIntent("sale");

        payment.setTransactions(createTransactions(paymentRequestDto));

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
        payment.setPayer(payer);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(paymentRequestDto.getFailedUrl());
        redirectUrls.setReturnUrl("http://localhost:8081/PAYPAL-SERVICE/api/v1/payment/execute");
        payment.setRedirectUrls(redirectUrls);

        APIContext apiContext = new APIContext(credentials.getMerchantId(), credentials.getMerchantPassword(), "sandbox");
        Payment createdPayment = payment.create(apiContext);

        PaymentRequest paymentRequest = PaymentRequest.builder().paymentId(createdPayment.getId())
                .merchantId(credentials.getMerchantId())
                .merchantSecret(credentials.getMerchantPassword())
                .amount(Double.parseDouble(createdPayment.getTransactions().get(0).getAmount().getTotal()))
                .currency("USD")
                .successUrl(paymentRequestDto.getSuccessUrl())
                .errorUrl(paymentRequestDto.getErrorUrl())
                .failedUrl(paymentRequestDto.getFailedUrl())
                .build();

        paymentRequestRepository.save(paymentRequest);

        return createdPayment;
    }

    private List<Transaction> createTransactions(PaymentRequestDto paymentRequestDto){
        List<Transaction> transactions = new ArrayList<>();

        Amount amount = new Amount();
        amount.setCurrency("USD");
        Double total = paymentRequestDto.getAmount().setScale(2, RoundingMode.HALF_UP).doubleValue();
        amount.setTotal(String.format("%.2f", total));

        Transaction transaction = new Transaction();
        transaction.setDescription("Website services");
        transaction.setAmount(amount);

        transactions.add(transaction);
        return transactions;
    }

    public String executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        PaymentRequest paymentRequest = paymentRequestRepository.findByPaymentId(paymentId);
        APIContext apiContext = new APIContext(paymentRequest.getMerchantId(), paymentRequest.getMerchantSecret(), "sandbox");
        Payment executedPayment = payment.execute(apiContext, paymentExecute);
        transactionService.save(executedPayment);
        if(executedPayment.getState().equals("approved")) {
            log.info("Payment was successfully executed. Payment id: {}", executedPayment.getId());
            return paymentRequest.getSuccessUrl();
        }
        log.info("Payment failed to execute. Payment id: {}", executedPayment.getId());
        return paymentRequest.getFailedUrl();
    }

}
