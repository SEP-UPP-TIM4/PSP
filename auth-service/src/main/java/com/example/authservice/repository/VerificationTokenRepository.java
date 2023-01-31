package com.example.authservice.repository;

import com.example.authservice.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findVerificationTokenByToken(String token);
    VerificationToken findVerificationTokenByUserId(String id);
}
