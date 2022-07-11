package com.in28minutes.microservice.currencyconversionservice.controller;

import com.in28minutes.microservice.currencyconversionservice.model.CurrencyCoversion;
import com.in28minutes.microservice.currencyconversionservice.proxy.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {

  @Autowired
  private CurrencyExchangeProxy proxy;

  @GetMapping(value = "/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyCoversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to,
                                                       @PathVariable BigDecimal quantity) {

    HashMap<String, String> uriVariables = new HashMap<>();
    uriVariables.put("from", from);
    uriVariables.put("to", to);

    var currencyConversion = new RestTemplate().getForEntity(
            "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
            CurrencyCoversion.class, uriVariables).getBody();

    return new CurrencyCoversion(currencyConversion.getId(),from, to, quantity,
            currencyConversion.getConversionMultiple(),
            quantity.multiply(currencyConversion.getConversionMultiple()),
            currencyConversion.getEnvironment());

  }

  @GetMapping(value = "/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyCoversion calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to,
                                                       @PathVariable BigDecimal quantity) {

    var currencyConversion = proxy.retrieveExchangeValue(from, to);

    return new CurrencyCoversion(currencyConversion.getId(),from, to, quantity,
            currencyConversion.getConversionMultiple(),
            quantity.multiply(currencyConversion.getConversionMultiple()),
            currencyConversion.getEnvironment() + " " + "feign");
  }
}
