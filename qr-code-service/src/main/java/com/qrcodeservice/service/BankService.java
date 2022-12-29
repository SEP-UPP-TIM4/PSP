package com.qrcodeservice.service;

import com.qrcodeservice.model.Bank;
import com.qrcodeservice.repository.BankRepository;
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
