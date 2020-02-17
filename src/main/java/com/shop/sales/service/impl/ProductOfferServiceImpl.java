package com.shop.sales.service.impl;

import com.shop.sales.bean.*;
import com.shop.sales.repository.SalesRepository;
import com.shop.sales.entity.OfferE;
import com.shop.sales.exception.SaleException;
import com.shop.sales.mapper.impl.ProductOfferMapper;
import com.shop.sales.service.OfferService;
import com.shop.sales.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;

@Service
public class ProductOfferServiceImpl implements OfferService<ProductOffer> {

  @Autowired SalesRepository salesRepository;

  @Autowired ProductOfferMapper mapper;

  @Override
  public Offer createOffer(ProductOffer po) throws SaleException {

    po.setOfferId(getId());
    po.setCreateDate(new Date());
    salesRepository.createOfferForProduct(mapper.toEntity(po));
    return po;
  }

  public ProductOffer getOffer(int id) throws SaleException {

    OfferE p = salesRepository.getOffer(id);

    ProductOffer po = mapper.toDto(p);

    if (ChronoUnit.DAYS.between(
            po.getCreateDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
            LocalDate.now())
        > Constants.OFFER_TIME_OUT_PERIOD_IN_DAYS) {
      throw new SaleException("Offer has been expired.");
    }

    return po;
  }

  private int getId(){
    return new Random().nextInt(Integer.MAX_VALUE);
  }
}
