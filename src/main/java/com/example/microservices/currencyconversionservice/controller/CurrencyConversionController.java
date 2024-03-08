package com.example.microservices.currencyconversionservice.controller;

import com.example.microservices.currencyconversionservice.entity.CurrencyConversion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {
    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from,
                                                 @PathVariable String to,
                                                 @PathVariable int quantity)
    {
        Map<String,String> uriVariables = new HashMap<>();
        uriVariables.put("from",from);
        uriVariables.put("to",to);

        ResponseEntity<CurrencyConversion> response = new RestTemplate()
                .getForEntity("http://localhost:8000/currency-exchange/from/USD/to/INR",
                        CurrencyConversion.class,
                        uriVariables);
        return response.getBody();
    }
}