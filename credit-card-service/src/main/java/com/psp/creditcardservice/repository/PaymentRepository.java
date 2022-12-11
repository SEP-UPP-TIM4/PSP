package com.psp.creditcardservice.repository;

import com.psp.creditcardservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
