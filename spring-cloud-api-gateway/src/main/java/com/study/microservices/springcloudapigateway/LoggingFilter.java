package com.study.microservices.springcloudapigateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@Component
public class LoggingFilter implements GlobalFilter {
    private Logger logger = Logger.getLogger(LoggingFilter.class.getName());
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("path of the request received -> " + exchange.getRequest().getPath());
        return chain.filter(exchange);
    }
}
