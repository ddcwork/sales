package com.shop.sales.mapper.impl;

import com.shop.sales.bean.*;
import com.shop.sales.entity.OfferE;
import com.shop.sales.entity.Product;
import com.shop.sales.mapper.EntityMapper;
import com.shop.sales.utility.OfferType;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * ProductOfferMapper, mapper class for product offer
 */
@Component
public class ProductOfferMapper implements EntityMapper<ProductOffer, OfferE> {

  @Override
  public OfferE toEntity(ProductOffer po) {
    OfferE o = new OfferE();
    o.setOfferId(po.getOfferId());
    o.setType(OfferType.PRODUCT.name());
    o.setOfferDescription(po.getDescription());
    o.setDiscountedPrice(po.getDiscountedPrice());
    o.setDiscountedPrice(po.getDiscountedPrice());
    o.setVat(po.getVat());
    o.setCreateDate(new java.sql.Date(po.getCreateDate().getTime()));
    o.setDiscountPercentage(po.getDiscountPercentage());
    Product p = new Product(po.getProdId());
    o.setProduct(p);
    return o;
  }

  @Override
  public ProductOffer toDto(OfferE o) {

    ProductOffer po = new ProductOffer();
    po.setProdId(o.getProduct().getId());
    po.setName(o.getProduct().getName());
    po.setPrice(o.getProduct().getPrice());
    po.setCurrency(o.getProduct().getCurrency());
    po.setDescription(o.getProduct().getDescription());
    po.setTermsAndConditions(o.getTermsAndConditions());
    po.setCreateDate(new Date(o.getCreateDate().getTime()));
    po.setOfferId(o.getOfferId());

    return po;
  }
}
