package com.shop.sales.service;

import com.shop.sales.bean.Offer;
import com.shop.sales.exception.SaleException;

/**
 * OfferService, interface for Offer Service
 * @param <T> type of class which extends offer bean
 */
public interface OfferService<T extends Offer> {

  Offer createOffer(T offer) throws SaleException;
}
