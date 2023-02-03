package com.study.microservices.currencyconversionservice.restproxy;

import com.study.microservices.currencyconversionservice.entities.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="currency-exchange", url = "http://localhost:8000")
//In absence of url, FeignClient tries to lookup url from discovery server through the microservice name
// and also load balances it.
@FeignClient(name="currency-exchange")

public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable("from") String from,
            @PathVariable("to") String to);
}
