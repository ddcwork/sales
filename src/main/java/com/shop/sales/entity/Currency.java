package com.shop.sales.entity;

import org.springframework.stereotype.Component;

/**
 * Currency, entity class for Currency table
 */
@Component
public class Currency {

  private int id;

  private String name;

  private String symbol;

  public Currency() {}

  public Currency(String name, String symbol) {
    this.name = name;
    this.symbol = symbol;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }
}
