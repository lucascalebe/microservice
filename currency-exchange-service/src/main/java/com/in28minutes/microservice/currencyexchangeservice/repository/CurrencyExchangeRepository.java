package com.in28minutes.microservice.currencyexchangeservice.repository;

import com.in28minutes.microservice.currencyexchangeservice.model.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
//  CurrencyExchange findByFromAndTo(String from, String to);

  Optional<CurrencyExchange> findByFromAndTo(String from, String to);

}
