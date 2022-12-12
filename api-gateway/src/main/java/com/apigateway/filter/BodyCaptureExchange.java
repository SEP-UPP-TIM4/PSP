package com.apigateway.filter;

import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebExchangeDecorator;

public class BodyCaptureExchange extends ServerWebExchangeDecorator {

    private BodyCaptureRequest bodyCaptureRequest;

    public BodyCaptureExchange(ServerWebExchange exchange) {
        super(exchange);
        this.bodyCaptureRequest = new BodyCaptureRequest(exchange.getRequest());
    }

    @Override
    public BodyCaptureRequest getRequest() {
        return bodyCaptureRequest;
    }

}
