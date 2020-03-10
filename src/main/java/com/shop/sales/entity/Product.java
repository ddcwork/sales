package com.shop.sales.entity;

import org.springframework.stereotype.Component;

/**
 * Product, entity class for Product table
 */
@Component
public class Product {

  private int id;

  private String name;

  private String description;

  private byte[] image;

  private String productInfo;

  private String termsAndConditions;

  private double price;

  private Currency currency;

  private String deliveryInfo;

  private double rating;

  public Product() {}

  public Product(int id) {
    this.id = id;
  }

  public Product(
      int id,
      String name,
      String description,
      byte[] image,
      String productInfo,
      String termsAndConditions,
      double price,
      Currency currency,
      String deliveryInfo,
      double rating) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.image = image;
    this.productInfo = productInfo;
    this.termsAndConditions = termsAndConditions;
    this.price = price;
    this.currency = currency;
    this.deliveryInfo = deliveryInfo;
    this.rating = rating;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public byte[] getImage() {
    return image;
  }

  public void setImage(byte[] image) {
    this.image = image;
  }

  public String getProductInfo() {
    return productInfo;
  }

  public void setProductInfo(String productInfo) {
    this.productInfo = productInfo;
  }

  public String getTermsAndConditions() {
    return termsAndConditions;
  }

  public void setTermsAndConditions(String termsAndConditions) {
    this.termsAndConditions = termsAndConditions;
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

  public String getDeliveryInfo() {
    return deliveryInfo;
  }

  public void setDeliveryInfo(String deliveryInfo) {
    this.deliveryInfo = deliveryInfo;
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }
}
