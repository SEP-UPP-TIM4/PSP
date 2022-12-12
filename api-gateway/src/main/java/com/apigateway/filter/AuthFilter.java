package com.apigateway.filter;

import com.apigateway.dto.CredentialsDto;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Component
public class AuthFilter implements GlobalFilter {

    private final WebClient.Builder webClientBuilder;

    public AuthFilter(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }


    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            GatewayFilterChain chain) {

        if (!exchange.getRequest().getURI().toString().contains("/payment?")) {
            return chain.filter(exchange);
        }

        String token = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

        String methodId = exchange.getRequest().getQueryParams().get("paymentMethodId").toString();
        methodId = decode(methodId);

        /* Trying to get request body */
        BodyCaptureExchange bodyCaptureExchange = new BodyCaptureExchange(exchange);
        chain.filter(bodyCaptureExchange).doFirst(() -> {
            System.out.println("*****Body request " + bodyCaptureExchange.getRequest().getFullBody());
        });
        /**/

        return webClientBuilder.build()
                .post()
                .uri("http://AUTH-SERVICE/api/v1/auth/validateToken?token=" + token + "&method=" + methodId)
                .retrieve().bodyToMono(CredentialsDto.class)
                .map(credentialsDto -> {
                    exchange.getRequest()
                            .mutate()
                            .header("x-auth-user-id", String.valueOf(credentialsDto.getUsername()))
                            .header("x-auth-user-secret", String.valueOf(credentialsDto.getPassword()))
                            .header("Location", String.valueOf(credentialsDto.getBankUrl()));
                    return exchange;
                }).flatMap(chain::filter);
    }

    private String decode(String methodId) {
        try {
            return String.valueOf(URLDecoder.decode(methodId, StandardCharsets.UTF_8.toString()).charAt(1));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
