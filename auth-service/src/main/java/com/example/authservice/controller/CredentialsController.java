package com.example.authservice.controller;

import com.example.authservice.dto.AddCredentialsRequestDto;
import com.example.authservice.dto.AddCredentialsResponseDto;
import com.example.authservice.dto.AddPaymentMethodResponseDto;
import com.example.authservice.dto.CredentialsDto;
import com.example.authservice.model.Credentials;
import com.example.authservice.service.CredentialsService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/credentials")
public class CredentialsController {

    private final CredentialsService credentialsService;
    private final String AUTH_HEADER = "Authorization";
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
        return modelMapper.map(newCredentials, AddCredentialsResponseDto.class);
    }

    @GetMapping
    @PreAuthorize("hasRole('MERCHANT')")
    @ResponseStatus(value = HttpStatus.OK)
    public List<CredentialsDto> getPaymentMethods() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        return modelMapper.map(credentialsService.findAll(username), new TypeToken<List<CredentialsDto>>() {}.getType());
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('MERCHANT')")
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePaymentMethod(@PathVariable Long id){
        credentialsService.delete(id);
    }


    public String getAuthHeaderFromHeader(HttpServletRequest request) {
        return request.getHeader(AUTH_HEADER);
    }
}
