package com.shop.sales.bean;

import com.shop.sales.entity.Address;
import com.shop.sales.entity.Currency;
import org.springframework.stereotype.Component;

/**
 * Product Offer, is the bean class for product offer entity
 */
@Component
public class ProductOffer extends Offer {

  private int prodId;

  private String name;

  private double price;

  private Currency currency;

  private String description;

  private Address shippingAddress;

  private byte[] image;

  private double discountedPrice;

  @Override
  public void calculateDiscountedPrice() {
    this.discountedPrice = ((price * this.getDiscountPercentage()) / 100) + this.getVat();
  }

  public int getProdId() {
    return prodId;
  }

  public void setProdId(int prodId) {
    this.prodId = prodId;
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

  public Address getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(Address shippingAddress) {
    this.shippingAddress = shippingAddress;
  }

  public byte[] getImage() {
    return image;
  }

  public void setImage(byte[] image) {
    this.image = image;
  }

  public double getDiscountedPrice() {
    calculateDiscountedPrice();
    return discountedPrice;
  }
}
