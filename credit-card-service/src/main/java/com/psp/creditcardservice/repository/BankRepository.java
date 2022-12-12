package com.psp.creditcardservice.repository;

import com.psp.creditcardservice.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {
    Bank findByUrl(String url);
}
