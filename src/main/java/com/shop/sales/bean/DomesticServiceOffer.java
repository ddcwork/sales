package com.shop.sales.bean;

import com.shop.sales.entity.Currency;
import com.shop.sales.utility.ServiceType;
import org.springframework.stereotype.Component;

@Component
public class DomesticServiceOffer extends Offer {

  private String name;

  private double price;

  private Currency currency;

  private String description;

  private ServiceType serviceType;

  private double serviceCharge;

  private double discountedPrice;

  @Override
  public void calculateDiscountedPrice() {

    this.discountedPrice =
        ((price * this.getDiscountPercentage()) / 100) + this.getVat() + serviceCharge;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ServiceType getServiceType() {
    return serviceType;
  }

  public void setServiceType(ServiceType serviceType) {
    this.serviceType = serviceType;
  }

  public double getServiceCharge() {
    return serviceCharge;
  }

  public void setServiceCharge(double serviceCharge) {
    this.serviceCharge = serviceCharge;
  }

  public double getDiscountedPrice() {
    return discountedPrice;
  }
}
