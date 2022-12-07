package com.example.authservice.service;

import com.example.authservice.model.Bank;
import com.example.authservice.repository.BankRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    private final BankRepository bankRepository;


    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public List<Bank> findAll(){
        return bankRepository.findAll();
    }
}
