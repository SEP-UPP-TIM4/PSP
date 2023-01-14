package com.psp.paypalservice.service;

import com.psp.paypalservice.dto.CredentialsDto;
import com.psp.paypalservice.dto.SubscriptionRequestDTO;
import com.psp.paypalservice.model.Subscription;
import com.psp.paypalservice.repository.SubscriptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class SubscriptionService {

    private final RestTemplate restTemplate;

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(RestTemplate restTemplate, SubscriptionRepository subscriptionRepository) {
        this.restTemplate = restTemplate;
        this.subscriptionRepository = subscriptionRepository;
    }

    public String subscribe(SubscriptionRequestDTO subscriptionRequestDTO, CredentialsDto credentials, String planId){
        JSONObject subscriptionRequest = new JSONObject();
        subscriptionRequest.put("plan_id", planId);
        subscriptionRequest.put("application_context", setApplicationContext(subscriptionRequestDTO));
        subscriptionRequest.put("quantity", "1");
        String midnightTime = LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.MIDNIGHT)
                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "Z";
        subscriptionRequest.put("start_time", midnightTime);

        HttpEntity<String> request = new HttpEntity<>(subscriptionRequest.toString(), setHeaders(credentials));
        ResponseEntity<String> response = restTemplate.postForEntity("https://api.sandbox.paypal.com/v1/billing/subscriptions", request, String.class);
        JSONObject responseJSON = new JSONObject(response.getBody());

        Subscription subscription = Subscription.builder().subscriptionId(responseJSON.getString("id"))
                .planId(planId)
                .merchantId(response.getHeaders().getFirst("Caller_acct_num"))
                .startTime(midnightTime)
                .build();
        subscriptionRepository.save(subscription);
        log.info("Subscription with id: {} added.", subscription.getSubscriptionId());
        return responseJSON.getJSONArray("links").getJSONObject(0).getString(("href"));
    }

    private HttpHeaders setHeaders(CredentialsDto credentials){
        HttpHeaders headers = new HttpHeaders();

        String authCredentials = credentials.getMerchantId() + ":" + credentials.getMerchantPassword();
        byte[] encodedAuthCredentials = Base64.encodeBase64(authCredentials.getBytes(StandardCharsets.US_ASCII));
        String authorizationHeader = "Basic " + new String(encodedAuthCredentials);

        headers.set("Authorization", authorizationHeader);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private JSONObject setApplicationContext(SubscriptionRequestDTO subscriptionRequestDTO){
        JSONObject applicationContext = new JSONObject();
        applicationContext.put("shipping_preference", "NO_SHIPPING");
        applicationContext.put("user_action", "SUBSCRIBE_NOW");
        applicationContext.put("return_url", subscriptionRequestDTO.getSuccessUrl());
        applicationContext.put("cancel_url", subscriptionRequestDTO.getFailedUrl());
        return applicationContext;
    }

}
