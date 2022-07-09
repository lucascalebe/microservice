package com.in28minutes.microservice.currencyconversionservice.controller;

import com.in28minutes.microservice.currencyconversionservice.model.CurrencyCoversion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {

  @GetMapping(value = "/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyCoversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to,
                                                       @PathVariable BigDecimal quantity) {

    return new CurrencyCoversion(10001L, from, to, quantity, BigDecimal.ONE, BigDecimal.ONE, "");
  }
}
