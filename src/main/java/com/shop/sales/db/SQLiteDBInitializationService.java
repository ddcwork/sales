package com.shop.sales.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import com.shop.sales.entity.Currency;
import com.shop.sales.entity.OfferE;
import com.shop.sales.entity.Product;
import com.shop.sales.utility.OfferType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * SQLiteDBInitializationService class, contains services for SQLite database creation for
 * database
 */
@Component
public class SQLiteDBInitializationService {

  private static final Logger logger = LoggerFactory.getLogger(SQLiteDBInitializationService.class);

  @Autowired SQLiteDataBase sqLiteDataBase;

  public void createDB() {

    sqLiteDataBase.createSQLiteDB("shoppingSales.db");

    Connection connection = null;

    try {
      connection = sqLiteDataBase.connect();
      logger.info("SQLite DB Connected");
    } catch (SQLException ex) {
      logger.error("Failed :", ex);
    } finally {
      try {
        if (connection != null) {
          connection.close();
        }
      } catch (SQLException ex) {
        logger.error("Failed :", ex);
      }
    }
  }

  public void prepareTables() {
    sqLiteDataBase.openConnection();

    prepareCurrency("British Pound", "£");
    prepareCurrency("US Doller", "$");

    createRecordOneInAllTables();
    createRecordTwoInAllTables();
    createRecordThreeInAllTables();

    sqLiteDataBase.closeConnection();
  }

  private void createRecordOneInAllTables() {

    Currency currency = getCurrency("£");

    Product product =
        prepareAndGetProduct(
            1,
            "DELL laptop",
            "DELL Inspiron",
            null,
            "product information",
            "product 1 additional information",
            400,
            currency,
            "Free Delivery in UK",
            4.0);

    OfferE o =
        new OfferE(
            1,
            OfferType.PRODUCT.name(),
            "offer 1",
            product,
            80,
            "new TC 1",
            10,
            20,
            new java.sql.Date(new Date().getTime()));

    buildUpOfferData(o);
  }

  private void createRecordTwoInAllTables() {

    Currency currency = getCurrency("$");

    Product product =
        prepareAndGetProduct(
            2,
            "DELL laptop",
            "DELL Latitude",
            null,
            "product information E7440",
            "product 2 additional information",
            450,
            currency,
            "Free Delivery in US",
            4.0);

    OfferE o =
        new OfferE(
            2,
            OfferType.PRODUCT.name(),
            "offer 2",
            product,
            70,
            "new TC 2",
            10,
            30,
            new java.sql.Date(new Date().getTime()));

    buildUpOfferData(o);
  }

  private void createRecordThreeInAllTables() {

    Currency currency = getCurrency("£");

    Product product =
        prepareAndGetProduct(
            3,
            "LENOVO laptop",
            "LENOVO Thinkpad",
            null,
            "product information",
            "product 3 additional information",
            430,
            currency,
            "Free Delivery in UK",
            4.0);

    OfferE o =
        new OfferE(
            3,
            OfferType.PRODUCT.name(),
            "offer 3",
            product,
            90,
            "new TC 3",
            10,
            10,
            java.sql.Date.valueOf("2020-01-10"));

    buildUpOfferData(o);
  }

  private Product prepareAndGetProduct(
      int id,
      String name,
      String description,
      byte[] image,
      String productInfo,
      String additionalInfo,
      double price,
      Currency currency,
      String deliveryInfo,
      double rating) {

    Product product =
        new Product(
            id,
            name,
            description,
            image,
            productInfo,
            additionalInfo,
            price,
            currency,
            deliveryInfo,
            rating);
    buildUpProductTable(product);
    return product;
  }

  private void prepareCurrency(String name, String symbol) {
    Currency currency = new Currency(name, symbol);
    buildUpCurrencyTable(currency);
  }

  private Currency getCurrency(String symbol) {
    return sqLiteDataBase.selectCurrency(symbol);
  }

  private void buildUpProductTable(Product product) {
    sqLiteDataBase.insertProductTable(product);
  }

  private void buildUpCurrencyTable(Currency currency) {
    sqLiteDataBase.insertCurrencyTable(currency);
  }

  private void buildUpOfferData(OfferE o) {

    sqLiteDataBase.insertOfferTable(o);
  }
}
