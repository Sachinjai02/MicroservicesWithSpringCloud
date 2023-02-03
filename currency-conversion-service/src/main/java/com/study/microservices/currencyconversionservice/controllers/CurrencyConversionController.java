package com.study.microservices.currencyconversionservice.controllers;

import com.study.microservices.currencyconversionservice.entities.CurrencyConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/currency-conversion")
public class CurrencyConversionController {

    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable("from") String from,
            @PathVariable("to") String to,
            @PathVariable("quantity") Double quantity
    ) {

        Map<String, String> params = new HashMap<>();
        params.put("from", from);
        params.put("to", to);
        CurrencyConversion conversion = restTemplate.getForObject("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class,
                params);

        conversion.setQuantity(quantity);
        conversion.setTotalCalculatedAmount(conversion.getConversionMultiple() * quantity);
        return conversion;
    }
}
