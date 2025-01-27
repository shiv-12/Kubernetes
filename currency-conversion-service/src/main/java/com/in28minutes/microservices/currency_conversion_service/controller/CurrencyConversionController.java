package com.in28minutes.microservices.currency_conversion_service.controller;

import com.in28minutes.microservices.currency_conversion_service.bean.CurrencyConversionEntity;
import com.in28minutes.microservices.currency_conversion_service.proxy.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
@RequestMapping("currency-conversion")
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    @Autowired
    private Environment environment;

    @GetMapping("/from/{fromCurrency}/to/{toCurrency}/quantity/{quantity}")
    public CurrencyConversionEntity calculateCurrencyConversion(
            @PathVariable String fromCurrency,
            @PathVariable String toCurrency,
            @PathVariable BigDecimal quantity) {
        CurrencyConversionEntity conversionEntityResponse =
                currencyExchangeProxy.getCurrencyExchange(fromCurrency, toCurrency);

        // Extra Field To Prepare ConversionEntityResponse
        conversionEntityResponse.setQuantity(quantity);
        conversionEntityResponse.setTotalCalculatedAmount(
                quantity.multiply(conversionEntityResponse.getConversionMultiple())
        );

        return conversionEntityResponse;
    }
}
