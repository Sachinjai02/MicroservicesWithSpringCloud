package com.study.microservices.currencyexchangeservice.controllers;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@RestController
public class CircuitBreakerController {
    private Logger logger = Logger.getLogger(CircuitBreakerController.class.getName());
    @GetMapping("/sample-api")
    @Retry(name="sample-api", fallbackMethod ="sampleAPIFallback")
    public String sampleAPI() {
        logger.info("Sample API call received");
        return new RestTemplate().getForObject("http://localhost:8080/some-dummy-url", String.class);
        //return "Sample API";
    }

    public String sampleAPIFallback(Exception ex) {
        return "fallback response for sample API";
    }
}
