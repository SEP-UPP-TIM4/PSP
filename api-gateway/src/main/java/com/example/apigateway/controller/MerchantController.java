package com.example.apigateway.controller;

import com.example.apigateway.dto.RegisterResponseDto;
import com.example.apigateway.dto.RegisterRequestDto;
import com.example.apigateway.model.Merchant;
import com.example.apigateway.service.MerchantService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/merchant")
public class MerchantController {

    private final MerchantService merchantService;

    private final ModelMapper modelMapper = new ModelMapper();

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public RegisterResponseDto add(@RequestBody RegisterRequestDto request) {
        Merchant newMerchant = merchantService.register(request.getName());
        return modelMapper.map(newMerchant, RegisterResponseDto.class);
    }


}
