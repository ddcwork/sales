package com.shop.sales.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * SQLiteDBInitialization class, contains initialisation process for creating initial
 * database
 */
@Component
public class SQLiteDBInitialization {

  @Autowired SQLiteDBInitializationService dbInitializationService;

  @Autowired SQLiteDataBase sqLiteDataBase;

  @PostConstruct
  public void init() {
    dbInitializationService.createDB();
    createTables();
    dbInitializationService.prepareTables();
  }

  private void createTables() {
    sqLiteDataBase.createAddressTable();
    sqLiteDataBase.createCurrencyTable();
    sqLiteDataBase.createDomesticServiceTable();
    sqLiteDataBase.createProductTable();
    sqLiteDataBase.createOfferTable();
  }
}
