package com.shop.sales.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Exception class for Shop Sales, wraps exception for sales */
public class SaleException extends Exception {

  private static final long serialVersionUID = 1L;

  private static final Logger logger = LoggerFactory.getLogger(SaleException.class);

  public SaleException(String errorDescription) {
    super(errorDescription);
    logger.error(errorDescription);
  }
}
