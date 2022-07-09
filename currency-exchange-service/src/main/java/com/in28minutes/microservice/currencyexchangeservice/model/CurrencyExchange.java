package com.in28minutes.microservice.currencyexchangeservice.model;

import java.math.BigDecimal;

public class CurrencyExchange {
  private Long id;
  private String from;
  private String to;
  private BigDecimal conversionMultiple;

  public CurrencyExchange() {
  }

  public CurrencyExchange(Long id, String from, String to, BigDecimal conversionMultiple) {
    this.id = id;
    this.from = from;
    this.to = to;
    this.conversionMultiple = conversionMultiple;
  }

  public Long getId() {
    return id;
  }

  public String getFrom() {
    return from;
  }

  public String getTo() {
    return to;
  }

  public BigDecimal getConversionMultiple() {
    return conversionMultiple;
  }
}
