package com.example.authservice.controller;

import com.example.authservice.dto.AddPaymentMethodResponseDto;
import com.example.authservice.dto.BankDto;
import com.example.authservice.service.BankService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/bank")
public class BankController {

    private final ModelMapper modelMapper = new ModelMapper();

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<BankDto> getBanks() {
        return modelMapper.map(bankService.findAll(), new TypeToken<List<BankDto>>() {}.getType());
    }
}
