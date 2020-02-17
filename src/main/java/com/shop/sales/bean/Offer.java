package com.shop.sales.bean;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public abstract class Offer {

  private int offerId;

  private String termsAndConditions;

  private int vat;

  private int discountPercentage;

  private Date createDate;

  public Offer() {}

  public abstract void calculateDiscountedPrice();

  public int getOfferId() {
    return offerId;
  }

  public void setOfferId(int offerId) {
    this.offerId = offerId;
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
}
