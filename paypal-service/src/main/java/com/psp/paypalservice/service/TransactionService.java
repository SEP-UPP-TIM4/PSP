package com.psp.paypalservice.service;

import com.paypal.api.payments.Payment;
import com.psp.paypalservice.model.Transaction;
import com.psp.paypalservice.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void save(Payment payment){
        Transaction transaction = Transaction.builder().paymentId(payment.getId())
                .payerId(payment.getPayer().getPayerInfo().getPayerId())
                .merchantId(payment.getTransactions().get(0).getPayee().getMerchantId())
                .amount(Double.parseDouble(payment.getTransactions().get(0).getAmount().getTotal()))
                .currency("USD")
                .timestamp(payment.getCreateTime())
                .status(payment.getState())
                .build();
        transactionRepository.save(transaction);
    }

}
