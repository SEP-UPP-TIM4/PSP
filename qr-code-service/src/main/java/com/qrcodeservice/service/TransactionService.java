package com.qrcodeservice.service;

import com.qrcodeservice.exception.NotFoundException;
import com.qrcodeservice.model.Transaction;
import com.qrcodeservice.model.TransactionStatus;
import com.qrcodeservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction updateStatus(Long transactionId, String transactionStatus) {
        Transaction transaction = getById(transactionId);
        transaction.setStatus(TransactionStatus.valueOf(transactionStatus));
        return save(transaction);
    }

    private Transaction getById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new NotFoundException(Transaction.class.getSimpleName()));
    }

    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

}
