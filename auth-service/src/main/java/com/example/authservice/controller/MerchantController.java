package com.example.authservice.controller;

import com.example.authservice.dto.RegisterRequestDto;
import com.example.authservice.dto.RegisterResponseDto;
import com.example.authservice.model.Merchant;
import com.example.authservice.service.MerchantService;
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
        Merchant newMerchant = merchantService.register(request.getName(), request.getUsername(), request.getPassword());
        RegisterResponseDto responseDto = modelMapper.map(newMerchant, RegisterResponseDto.class);
        responseDto.setUsername(newMerchant.getUser().getUsername());
        return responseDto;
    }


}
