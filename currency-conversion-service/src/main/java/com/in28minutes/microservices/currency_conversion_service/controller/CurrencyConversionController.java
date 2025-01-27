package com.in28minutes.microservices.currency_conversion_service.controller;

import com.in28minutes.microservices.currency_conversion_service.bean.CurrencyConversion;
import com.in28minutes.microservices.currency_conversion_service.proxy.CurrencyExchangeProxy;
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
@RequestMapping("currency-conversion")
public class CurrencyConversionController {

    private Logger logger = LoggerFactory.getLogger(CurrencyConversionController.class);

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    @Autowired
    private Environment environment;

    @GetMapping("/from/{fromCurrency}/to/{toCurrency}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String fromCurrency,
            @PathVariable String toCurrency,
            @PathVariable BigDecimal quantity) {

        // KUBERNETES-CHANGE
        logger.info("CurrencyConversionController Called with {} to {} with {}",
                fromCurrency, toCurrency, quantity
        );


        CurrencyConversion currencyConversion =
                currencyExchangeProxy.getCurrencyExchange(fromCurrency, toCurrency);

        // Extra Field To Prepare ConversionEntityResponse
        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculatedAmount(
                quantity.multiply(currencyConversion.getConversionMultiple())
        );

        return currencyConversion;
    }
}
