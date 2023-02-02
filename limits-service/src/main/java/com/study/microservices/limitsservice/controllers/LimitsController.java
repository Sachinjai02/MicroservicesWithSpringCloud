package com.study.microservices.limitsservice.controllers;

import com.study.microservices.limitsservice.bean.Limits;
import com.study.microservices.limitsservice.configuration.LimitsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private LimitsConfiguration limitsConfiguration;

    @GetMapping("/limits")
    public Limits retrieveLimits() {
        return new Limits(limitsConfiguration.getMinimum(), limitsConfiguration.getMaximum());
    }
}
