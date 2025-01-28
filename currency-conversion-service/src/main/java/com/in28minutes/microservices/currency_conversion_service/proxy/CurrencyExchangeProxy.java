package com.in28minutes.microservices.currency_conversion_service.proxy;

import com.in28minutes.microservices.currency_conversion_service.bean.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Adding application.name and base url of CurrencyExchangeService

//@FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_SERVICE_HOST:http://localhost}:8000")
@FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_URI:http://localhost}:8000")
public interface CurrencyExchangeProxy {
    @GetMapping("/currency-exchange/from/{currency_from}/to/{currency_to}")
    public CurrencyConversion getCurrencyExchange(
            @PathVariable String currency_from,
            @PathVariable String currency_to);
}
