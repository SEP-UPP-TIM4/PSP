package com.example.authservice.service;

import com.example.authservice.model.VerificationToken;
import com.example.authservice.repository.VerificationTokenRepository;
import org.springframework.stereotype.Service;

@Service
public class VerificationTokenService {
    private final VerificationTokenRepository verificationTokenRepository;

    public VerificationTokenService(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    public VerificationToken saveVerificationToken(VerificationToken token) {
        return verificationTokenRepository.save(token);
    }

    public VerificationToken findVerificationTokenByToken(String token) {
        return verificationTokenRepository.findVerificationTokenByToken(token);
    }

    public VerificationToken findVerificationTokenByUser(String id) {
        return verificationTokenRepository.findVerificationTokenByUserId(id);
    }

    public void delete(VerificationToken verificationToken) {
        verificationTokenRepository.delete(verificationToken);
    }
}
