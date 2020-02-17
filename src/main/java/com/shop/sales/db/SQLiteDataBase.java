package com.shop.sales.db;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.shop.sales.entity.OfferE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.shop.sales.entity.Currency;
import com.shop.sales.entity.Product;

/** SQLiteDataBase class, contains SQLite database CRUD operations for creating database */
@Component
public class SQLiteDataBase {

  private static final Logger logger = LoggerFactory.getLogger(SQLiteDataBase.class);

  private String url;
  private Connection connection;

  @Autowired Environment environment;

  public void createSQLiteDB(String fileName) {

    prepareUrl(fileName);

    try {
      Class.forName("org.sqlite.JDBC");
    } catch (ClassNotFoundException e) {
      logger.error(e.getMessage());
    }

    try (Connection conn = connect()) {
      if (conn != null) {
        DatabaseMetaData meta = conn.getMetaData();
        logger.info("The driver name is ", meta.getDriverName());
        logger.info("A new database has been created.");
      }

    } catch (SQLException e) {
      logger.error(e.getMessage());
    } finally {
      closeConnection();
    }
  }

  private void prepareUrl(String fileName) {

    File sqliteDBfile = new File(environment.getProperty("file.path"));

    try {
      sqliteDBfile.mkdirs();
      sqliteDBfile.createNewFile();
    } catch (IOException e) {
      logger.error(e.getMessage());
    }

    this.url = getUrl(fileName, sqliteDBfile);
  }

  private String getUrl(String fileName, File sqliteDBfile) {
    StringBuilder filePath = new StringBuilder();
    filePath.append(sqliteDBfile.getAbsolutePath()).append("\\").append(fileName);
    logger.info("Sqlite DB File created in the path ", filePath.toString());
    return new StringBuilder("jdbc:sqlite:").append(filePath).toString();
  }

  public Connection openConnection() {
    try {
      connection = connect();
    } catch (SQLException e) {
      logger.error(e.getMessage());
      connection = null;
    }
    return connection;
  }

  public void closeConnection() {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        logger.error(e.getMessage());
      }
    }
  }

  Connection connect() throws SQLException {
    return DriverManager.getConnection(url);
  }

  public void createCurrencyTable() {

    String sql =
        "CREATE TABLE IF NOT EXISTS currency "
            + "(\n"
            + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
            + "	name text NOT NULL,\n"
            + "	symbol text NOT NULL UNIQUE"
            + ");";

    try (Connection conn = connect();
        Statement stmt = conn.createStatement()) {
      // create a new table
      stmt.execute(sql);
    } catch (SQLException e) {
      logger.error(e.getMessage());
    }
  }

  public void insertCurrencyTable(Currency currency) {
    String sql = "INSERT INTO currency (name, symbol) VALUES(?,?)";

    try (Connection conn = connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, currency.getName());
      pstmt.setString(2, currency.getSymbol());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      logger.error(e.getMessage());
    }
  }

  public Currency selectCurrency(String symbol) {

    String sql = "SELECT id, name, symbol FROM currency WHERE symbol = ?";

    try (Connection conn = connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, symbol);
      Currency currency;
      try (ResultSet rs = pstmt.executeQuery()) {

        currency = new Currency();
        currency.setId(rs.getInt("id"));
        currency.setName(rs.getString("name"));
        currency.setSymbol(rs.getString("symbol"));
      }
      return currency;

    } catch (SQLException e) {
      logger.error(e.getMessage());
    }
    return null;
  }

  public void createProductTable() {

    String sql =
        "CREATE TABLE IF NOT EXISTS product "
            + "(\n"
            + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
            + "	name text NOT NULL,\n"
            + "	description text NOT NULL,\n"
            + "	image blob,\n"
            + "	productInfo text NOT NULL,\n"
            + "	price integer NOT NULL,\n"
            + "	currencyId integer NOT NULL,\n"
            + "	deliveryInfo text NOT NULL,\n"
            + "	rating real,\n"
            + " FOREIGN KEY (currencyId) REFERENCES currency(id)"
            + ");";

    try (Connection conn = connect();
        Statement stmt = conn.createStatement()) {
      stmt.execute(sql);
    } catch (SQLException e) {
      logger.error(e.getMessage());
    }
  }

  public void insertProductTable(Product product) {
    String sql =
        "INSERT INTO product (name, description, image, productInfo,"
            + " price, currencyId, deliveryInfo, rating, id)"
            + " VALUES(?,?,?,?,?,?,?,?,?)";

    try (Connection conn = connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, product.getName());
      pstmt.setString(2, product.getDescription());
      pstmt.setBytes(3, product.getImage());
      pstmt.setString(4, product.getProductInfo());
      pstmt.setDouble(5, product.getPrice());
      pstmt.setInt(6, product.getCurrency().getId());
      pstmt.setString(7, product.getDeliveryInfo());
      pstmt.setDouble(8, product.getRating());
      pstmt.setInt(9, product.getId());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      logger.error(e.getMessage());
    }
  }

  public void createOfferTable() {

    String sql =
        "CREATE TABLE IF NOT EXISTS offer "
            + "(\n"
            + "	offerId integer PRIMARY KEY AUTOINCREMENT,\n"
            + "	type text,\n"
            + "	offerDescription text,\n"
            + "	discountedPrice integer,\n"
            + "	termsAndConditions text,\n"
            + "	vat integer,\n"
            + "	discountPercentage integer,\n"
            + "	productId integer,\n"
            + "	domesticServiceId integer,\n"
            + "	createDate date NOT NULL"
            + ");";

    try (Connection conn = connect();
        Statement stmt = conn.createStatement()) {
      stmt.execute(sql);
    } catch (SQLException e) {
      logger.error(e.getMessage());
    }
  }

  public void insertOfferTable(OfferE offer) {
    String sql =
        "INSERT INTO offer (offerId, type, offerDescription, productId,"
            + "domesticServiceId, discountedPrice, termsAndConditions, vat,"
            + "discountPercentage, createDate) VALUES(?,?,?,?,?,?,?,?,?,?)";

    try (Connection conn = connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, offer.getOfferId());
      pstmt.setString(2, offer.getType());
      pstmt.setString(3, offer.getOfferDescription());
      pstmt.setInt(4, offer.getProduct().getId());
      pstmt.setDouble(6, offer.getDiscountedPrice());
      pstmt.setString(7, offer.getTermsAndConditions());
      pstmt.setInt(8, offer.getVat());
      pstmt.setInt(9, offer.getDiscountPercentage());
      pstmt.setDate(10, offer.getCreateDate());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      logger.error(e.getMessage());
    }
  }

  // Todo: need full implementation
  public void createDomesticServiceTable() {

    String sql =
        "CREATE TABLE IF NOT EXISTS domesticService "
            + "(\n"
            + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
            + "	name text NOT NULL"
            + ");";

    try (Connection conn = connect();
        Statement stmt = conn.createStatement()) {
      stmt.execute(sql);
    } catch (SQLException e) {
      logger.error(e.getMessage());
    }
  }
  // Todo: need full implementation
  public void createAddressTable() {

    String sql =
        "CREATE TABLE IF NOT EXISTS address "
            + "(\n"
            + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
            + "	number integer NOT NULL,\n"
            + "	postcode string NOT NULL"
            + ");";

    try (Connection conn = connect();
        Statement stmt = conn.createStatement()) {
      stmt.execute(sql);
    } catch (SQLException e) {
      logger.error(e.getMessage());
    }
  }
}
