package com.in28minutes.microservice.currencyexchangeservice.controller;

import com.in28minutes.microservice.currencyexchangeservice.model.CurrencyExchange;
import com.in28minutes.microservice.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

  @Autowired
  private Environment environment;

  @Autowired
  private CurrencyExchangeRepository currencyExchangeRepository;

  @GetMapping("/currency-exchange/from/{from}/to/{to}")
  public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
    var currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to)
            .orElseThrow(() -> new RuntimeException(String.format("Unable to find data from %s to %s", from, to)));

    String port = environment.getProperty("local.server.port");
    currencyExchange.setEnvironment(port);
    return currencyExchange;
  }
}
