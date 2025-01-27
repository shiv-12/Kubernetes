package com.in28minutes.microservices.currency_exchange_service.controller;

import com.in28minutes.microservices.currency_exchange_service.bean.CurrencyExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    // Creating logger
    private final Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);


    @Autowired
    private Environment environment;    // From : org.springframework.core.env

    @GetMapping("/from/{currency_from}/to/{currency_to}")     // URL ==> /from/USD/to/INR
    public CurrencyExchange getCurrencyExchange(
            @PathVariable String currency_from,
            @PathVariable String currency_to) {

        logger.info("getCurrencyExchange called with {} to {}", currency_from, currency_to);

        CurrencyExchange currencyExchange = new CurrencyExchange();
        currencyExchange.setId(1L);
        currencyExchange.setFrom("USD");
        currencyExchange.setTo("INR");
        currencyExchange.setConversionMultiple(BigDecimal.valueOf(65));

        // Setting The Environment Details
        String instancePort = environment.getProperty("local.server.port");     // Server PORT
        String host = environment.getProperty("HOSTNAME");                      // POD Information
        String version = "v11";                                                 // Same as POM.XML
        currencyExchange.setEnvironment(instancePort + " " + host + " " + version);

        return currencyExchange;
    }
}
