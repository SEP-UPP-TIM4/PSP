package com.example.authservice.repository;

import com.example.authservice.model.SubscriptionRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRequestRepository extends JpaRepository<SubscriptionRequest, Long> {
}
