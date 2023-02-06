package com.study.microservices.currencyexchangeservice.controllers;

import com.study.microservices.currencyexchangeservice.models.CurrencyExchange;
import com.study.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    private Logger logger = Logger.getLogger(CurrencyExchangeController.class.getName());
    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;
    @Autowired
    private Environment environment;

    @GetMapping("/from/{fromCurr}/to/{toCurr}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable("fromCurr") String from,
                                                  @PathVariable("toCurr") String to) {
        logger.info("retrieveExchangeValue called with " + from + " to " + to);
        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
        if(currencyExchange == null) {
            throw new RuntimeException("Unable to find conversion data for from " + from  + " to " + to);
        }
        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
        return currencyExchange;
    }
}
