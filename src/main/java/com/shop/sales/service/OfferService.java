package com.shop.sales.service;

import com.shop.sales.bean.Offer;
import com.shop.sales.exception.SaleException;

public interface OfferService<T extends Offer> {

  Offer createOffer(T offer) throws SaleException;
}
