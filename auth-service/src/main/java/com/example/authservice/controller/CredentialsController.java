package com.example.authservice.controller;

import com.example.authservice.dto.*;
import com.example.authservice.mapper.CredentialsMapper;
import com.example.authservice.model.Credentials;
import com.example.authservice.service.CredentialsService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "api/v1/credentials")
public class CredentialsController {

    private final CredentialsService credentialsService;
    private final ModelMapper modelMapper = new ModelMapper();

    public CredentialsController(CredentialsService credentialsService) {

        this.credentialsService = credentialsService;
    }

    @PostMapping
    @PreAuthorize("hasRole('MERCHANT')")
    @ResponseStatus(value = HttpStatus.OK)
    public AddCredentialsResponseDto add(@RequestBody AddCredentialsRequestDto request) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        Credentials newCredentials = credentialsService.add(request, username);
        log.info("Credentials successfully added. Username: {}", request.getUsername());
        return modelMapper.map(newCredentials, AddCredentialsResponseDto.class);
    }

    @GetMapping
    @PreAuthorize("hasRole('MERCHANT')")
    @ResponseStatus(value = HttpStatus.OK)
    public List<CredentialsDto> getPaymentMethods() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        log.info("Payment methods successfully gotten. Username: {}", username);
        return credentialsService.findAll(username).stream()
                .map(CredentialsMapper::CredentialsToCredentialsDto).collect(Collectors.toList());

    }

    @GetMapping(value = "/payment-request/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<PaymentProcessingDto> getByPaymentRequest(@PathVariable Long id) {
        return credentialsService.findByPaymentRequest(id).stream()
                .map(credential -> CredentialsMapper.CredentialsToPaymentProcessingDto(credential, id)).collect(Collectors.toList());

    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('MERCHANT')")
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePaymentMethod(@PathVariable Long id){
        credentialsService.delete(id);
    }
}
