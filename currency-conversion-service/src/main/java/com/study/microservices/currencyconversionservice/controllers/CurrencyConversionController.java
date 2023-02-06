package com.study.microservices.currencyconversionservice.controllers;

import com.study.microservices.currencyconversionservice.entities.CurrencyConversion;
import com.study.microservices.currencyconversionservice.restproxy.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/currency-conversion")
public class CurrencyConversionController {

    private Logger logger = Logger.getLogger(CurrencyConversionController.class.getName());
    @Autowired
    private CurrencyExchangeProxy proxy;
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable("from") String from,
            @PathVariable("to") String to,
            @PathVariable("quantity") Double quantity
    ) {
        logger.info("calculateCurrencyConversion called with " + from + " to " + to + " quantity " + quantity);
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

    @GetMapping("feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(
            @PathVariable("from") String from,
            @PathVariable("to") String to,
            @PathVariable("quantity") Double quantity
    ) {

        CurrencyConversion conversion = proxy.calculateCurrencyConversion(from, to);
        conversion.setQuantity(quantity);
        conversion.setTotalCalculatedAmount(conversion.getConversionMultiple() * quantity);
        return conversion;
    }
}
