package com.psp.creditcardservice.service;

import com.psp.creditcardservice.model.Bank;
import com.psp.creditcardservice.repository.BankRepository;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public Bank getByUrl(String url) {
        return bankRepository.findByUrl(url);
    }
}
