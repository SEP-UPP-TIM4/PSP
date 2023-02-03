package com.example.authservice.controller;

import com.example.authservice.dto.SubscriptionMethodInfoDTO;
import com.example.authservice.dto.SubscriptionRequestDTO;
import com.example.authservice.model.SubscriptionRequest;
import com.example.authservice.service.SubscriptionRequestService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Slf4j
@RequestMapping(value = "api/v1/subscription-request")
public class SubscriptionRequestController {

    private final ModelMapper modelMapper = new ModelMapper();

    private final SubscriptionRequestService subscriptionRequestService;

    public SubscriptionRequestController(SubscriptionRequestService subscriptionRequestService) {
        this.subscriptionRequestService = subscriptionRequestService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public SubscriptionMethodInfoDTO add(@RequestBody SubscriptionRequestDTO request, ModelMap model) throws IOException {
        SubscriptionMethodInfoDTO subscriptionMethodInfoDTO = subscriptionRequestService.add(modelMapper.map(request, SubscriptionRequest.class));
        if(subscriptionMethodInfoDTO == null) log.info("Subscription request failed to be added. Api key: {}", request.getApiKey());
        log.info("Subscription request successfully added. Api key: {}", request.getApiKey());
        return subscriptionMethodInfoDTO;
    }
}
