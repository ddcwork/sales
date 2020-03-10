package com.shop.sales.service.impl;

import com.shop.sales.bean.DomesticServiceOffer;
import com.shop.sales.bean.Offer;
import com.shop.sales.service.OfferService;
import org.springframework.stereotype.Service;

/**
 * DomesticServiceOfferServiceImpl, service implementation class for Domestic Service Offer
 */
@Service
public class DomesticServiceOfferServiceImpl implements OfferService<DomesticServiceOffer> {

  @Override
  public Offer createOffer(DomesticServiceOffer offer) {
    // TODO: need implementation in future
    return new DomesticServiceOffer();
  }
}
