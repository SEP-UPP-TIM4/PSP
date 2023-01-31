package com.example.demo.config;

import com.example.demo.errorhandling.ClientErrorHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfig {

    private static final String COINGATE_URL = "https://api-sandbox.coingate.com/v2/orders";

    @Value("${api.token}")
    private String apiToken;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .rootUri(COINGATE_URL)
                .errorHandler(new ClientErrorHandler())
//                .additionalInterceptors((request, body, execution) -> {
//                    request.getHeaders().add("Authorization", "Bearer " + apiToken);
//                    request.getHeaders().setContentType(MediaType.APPLICATION_JSON);
//                    return execution.execute(request, body);
//                })
                .build();
    }

}
