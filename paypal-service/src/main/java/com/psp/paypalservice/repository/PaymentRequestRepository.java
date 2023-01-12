package com.psp.paypalservice.repository;

import com.psp.paypalservice.model.PaymentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRequestRepository extends JpaRepository<PaymentRequest, Long> {
    PaymentRequest findByPaymentId(String paymentId);
}
