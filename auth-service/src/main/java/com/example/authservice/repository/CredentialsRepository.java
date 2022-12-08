package com.example.authservice.repository;

import com.example.authservice.model.Credentials;
import com.example.authservice.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface CredentialsRepository extends JpaRepository<Credentials, Long> {
    @Query(value = "SELECT * FROM credentials WHERE merchant_id=?1", nativeQuery = true)
    Set<Credentials> findByMerchantId(UUID id);

}
