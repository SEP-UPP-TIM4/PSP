package com.psp.creditcardservice.service;

import com.psp.creditcardservice.dto.PaymentRequestDto;
import com.psp.creditcardservice.model.Currency;
import com.psp.creditcardservice.model.Payment;
import com.psp.creditcardservice.model.Transaction;
import com.psp.creditcardservice.model.TransactionStatus;
import com.psp.creditcardservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment createNewPayment(PaymentRequestDto requestDto) {
        // TODO: bank url?
        Transaction transaction = Transaction.builder().amount(requestDto.getAmount()).currency(Currency.RSD).build();
        return paymentRepository.save(Payment.builder().merchantId("merchantId").merchantPassword("merchantPassword").transaction(transaction).build());
    }
}
