package com.shop.sales.controller;

import com.shop.sales.bean.Offer;
import com.shop.sales.bean.ProductOffer;
import com.shop.sales.exception.SaleException;
import com.shop.sales.service.impl.ProductOfferServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Product sales controller, contains the end-points to product sales
 */
@RestController
@RequestMapping("shopping")
public class ProductSales {

  @Autowired private ProductOfferServiceImpl productOfferServiceImpl;

  public ProductSales(ProductOfferServiceImpl productOfferServiceImpl) {
    this.productOfferServiceImpl = productOfferServiceImpl;
  }

  @PostMapping("offer")
  public ResponseEntity createOffer(@RequestBody ProductOffer offer) {

    try {
      Offer o = productOfferServiceImpl.createOffer(offer);
      return ResponseEntity.ok(o);
    } catch (SaleException e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
  }

  @GetMapping("offer/{id}")
  public ResponseEntity getOffer(@PathVariable("id") int id) {

    try {
      ProductOffer po = productOfferServiceImpl.getOffer(id);
      return ResponseEntity.ok(po);
    } catch (SaleException e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
  }
}
