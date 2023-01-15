package com.psp.paypalservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SubscriptionRequestDTO {

    private String successUrl;

    private String failedUrl;

    private String errorUrl;
}
