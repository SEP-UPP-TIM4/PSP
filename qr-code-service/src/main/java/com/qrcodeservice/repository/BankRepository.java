package com.qrcodeservice.repository;

import com.qrcodeservice.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {
    Bank findByUrl(String url);
}
