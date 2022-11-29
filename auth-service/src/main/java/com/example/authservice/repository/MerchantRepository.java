package com.example.authservice.repository;

import com.example.authservice.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MerchantRepository extends JpaRepository<Merchant, UUID> {

    Optional<Merchant> findByName(String name);
    Optional<Merchant> findByApiKey(String apiKey);
}
