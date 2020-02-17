package com.shop.sales.entity;

import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class OfferE {

  private int offerId;

  private String type;

  private String offerDescription;

  private Product product;

  private DomesticService domesticService;

  private double discountedPrice;

  private String termsAndConditions;

  private int vat;

  private int discountPercentage;

  private Date createDate;

  public OfferE() {}

  public OfferE(
      int offerId,
      String type,
      String offerDescription,
      Product product,
      double discountedPrice,
      String termsAndConditions,
      int vat,
      int discountPercentage,
      Date createDate) {
    this.offerId = offerId;
    this.type = type;
    this.offerDescription = offerDescription;
    this.product = product;
    this.discountedPrice = discountedPrice;
    this.termsAndConditions = termsAndConditions;
    this.vat = vat;
    this.discountPercentage = discountPercentage;
    this.createDate = createDate;
  }

  public OfferE(
      int offerId,
      String type,
      String offerDescription,
      DomesticService domesticService,
      double discountedPrice,
      String termsAndConditions,
      int vat,
      int discountPercentage,
      Date createDate) {
    this.offerId = offerId;
    this.type = type;
    this.offerDescription = offerDescription;
    this.domesticService = domesticService;
    this.discountedPrice = discountedPrice;
    this.termsAndConditions = termsAndConditions;
    this.vat = vat;
    this.discountPercentage = discountPercentage;
    this.createDate = createDate;
  }

  public OfferE(int offerId, String offerType, String offerDescription, Product product) {

    this.offerId = offerId;
    this.type = offerType;
    this.offerDescription = offerDescription;
    this.product = product;
  }

  public String getTermsAndConditions() {
    return termsAndConditions;
  }

  public void setTermsAndConditions(String termsAndConditions) {
    this.termsAndConditions = termsAndConditions;
  }

  public int getVat() {
    return vat;
  }

  public void setVat(int vat) {
    this.vat = vat;
  }

  public int getDiscountPercentage() {
    return discountPercentage;
  }

  public void setDiscountPercentage(int discountPercentage) {
    this.discountPercentage = discountPercentage;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public double getDiscountedPrice() {
    return discountedPrice;
  }

  public void setDiscountedPrice(double discountedPrice) {
    this.discountedPrice = discountedPrice;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public DomesticService getDomesticService() {
    return domesticService;
  }

  public void setDomesticService(DomesticService domesticService) {
    this.domesticService = domesticService;
  }

  public int getOfferId() {
    return offerId;
  }

  public void setOfferId(int offerId) {
    this.offerId = offerId;
  }

  public String getOfferDescription() {
    return offerDescription;
  }

  public void setOfferDescription(String offerDescription) {
    this.offerDescription = offerDescription;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }
}
