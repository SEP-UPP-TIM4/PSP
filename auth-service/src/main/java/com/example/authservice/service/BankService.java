package com.example.authservice.service;

import com.example.authservice.exception.NotFoundException;
import com.example.authservice.model.Bank;
import com.example.authservice.repository.BankRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankService {

    private final BankRepository bankRepository;


    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public Bank findById(Long id){
        return bankRepository.findById(id).orElseThrow(() -> new NotFoundException(Bank.class.getSimpleName()));
    }
    public List<Bank> findAll(){
        return bankRepository.findAll();
    }
}
