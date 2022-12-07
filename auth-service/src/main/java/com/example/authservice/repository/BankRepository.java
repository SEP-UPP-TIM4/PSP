package com.example.authservice.repository;

import com.example.authservice.model.Bank;
import com.example.authservice.model.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {
}
