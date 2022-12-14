package com.psp.creditcardservice.service;

import com.psp.creditcardservice.exception.NotFoundException;
import com.psp.creditcardservice.model.Transaction;
import com.psp.creditcardservice.model.TransactionStatus;
import com.psp.creditcardservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction updateStatus(Long transactionId, String transactionStatus) {
        Transaction transaction = getById(transactionId);
        transaction.setStatus(TransactionStatus.valueOf(transactionStatus));
        return save(transaction);
    }

    private Transaction getById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new NotFoundException(Transaction.class.getSimpleName()));
    }
}
