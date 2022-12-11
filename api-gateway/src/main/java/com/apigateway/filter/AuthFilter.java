package com.apigateway.filter;

import com.apigateway.dto.CredentialsDto;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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


        if(!exchange.getRequest().getURI().getPath().contains("/payment?")) {
            return chain.filter(exchange);
        }

        String token = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

        String methodId = exchange.getRequest().getQueryParams().get("paymentMethodId").toString();

        return webClientBuilder.build()
                .post()
                .uri("http://AUTH-SERVICE/api/v1/auth/validateToken?token=" + token + "&method=" + methodId)
                .retrieve().bodyToMono(CredentialsDto.class)
                .map(credentialsDto -> {
                    System.out.println(credentialsDto);
                    exchange.getRequest()
                            .mutate()
                            .header("x-auth-user-id", String.valueOf(credentialsDto.getUsername()))
                            .header("x-auth-user-secret", String.valueOf(credentialsDto.getPassword()))
                            .header("Location", String.valueOf(credentialsDto.getBankUrl()));
                    return exchange;
                }).flatMap(chain::filter);
    }
}
