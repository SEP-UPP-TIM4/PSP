package com.example.authservice.repository;

import com.example.authservice.model.Merchant;
import com.example.authservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface MerchantRepository extends JpaRepository<Merchant, UUID> {

    @Query(value = "SELECT * FROM merchant WHERE user_id=?1", nativeQuery = true)
    Optional<Merchant> findByUserId(Long id);
    Optional<Merchant> findByName(String name);
    Optional<Merchant> findByApiKey(String apiKey);
}
