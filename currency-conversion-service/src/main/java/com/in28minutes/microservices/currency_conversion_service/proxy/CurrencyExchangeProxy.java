package com.in28minutes.microservices.currency_conversion_service.proxy;

import com.in28minutes.microservices.currency_conversion_service.bean.CurrencyConversionEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Adding application.name and base url of CurrencyExchangeService

@FeignClient(name = "currency-exchange", url = "localhost:8000")
public interface CurrencyExchangeProxy {
    @GetMapping("/currency-exchange/from/{currency_from}/to/{currency_to}")
    public CurrencyConversionEntity getCurrencyExchange(
            @PathVariable String currency_from,
            @PathVariable String currency_to);
}
