package com.example.authservice.controller;

import com.example.authservice.dto.AddCredentialsRequestDto;
import com.example.authservice.dto.AddCredentialsResponseDto;
import com.example.authservice.model.Credentials;
import com.example.authservice.service.CredentialsService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    @ResponseStatus(value = HttpStatus.OK)
    public AddCredentialsResponseDto add(@RequestBody AddCredentialsRequestDto request, HttpServletRequest servletRequest) {
        String apiKey = getAuthHeaderFromHeader(servletRequest);
        Credentials newCredentials = credentialsService.add(request, apiKey);
        return modelMapper.map(newCredentials, AddCredentialsResponseDto.class);
    }

    public String getAuthHeaderFromHeader(HttpServletRequest request) {
        return request.getHeader(AUTH_HEADER);
    }
}
