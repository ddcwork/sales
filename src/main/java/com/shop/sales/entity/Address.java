package com.shop.sales.entity;

import org.springframework.stereotype.Component;

/**
 * Address, entity class for address table
 */
@Component
public class Address {

  private String number;

  private String addressFirstLine;

  private String addressSecondLine;

  private String state;

  private String country;

  private String postcode;

  public Address() {}

  public Address(
      String number,
      String addressFirstLine,
      String addressSecondLine,
      String state,
      String country,
      String postcode) {
    this.number = number;
    this.addressFirstLine = addressFirstLine;
    this.addressSecondLine = addressSecondLine;
    this.state = state;
    this.country = country;
    this.postcode = postcode;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getAddressFirstLine() {
    return addressFirstLine;
  }

  public void setAddressFirstLine(String addressFirstLine) {
    this.addressFirstLine = addressFirstLine;
  }

  public String getAddressSecondLine() {
    return addressSecondLine;
  }

  public void setAddressSecondLine(String addressSecondLine) {
    this.addressSecondLine = addressSecondLine;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getPostcode() {
    return postcode;
  }

  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }
}
