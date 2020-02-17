package com.shop.sales.repository;

import com.shop.sales.db.SQLiteDataBase;
import com.shop.sales.entity.Currency;
import com.shop.sales.entity.OfferE;
import com.shop.sales.entity.Product;
import com.shop.sales.exception.SaleException;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class SalesRepository {

  private static final Logger logger = LoggerFactory.getLogger(SalesRepository.class);

  @Autowired private SQLiteDataBase sqLiteDataBase;

  private Connection conn;

  public void createOfferForProduct(OfferE o) throws SaleException {

    sqLiteDataBase.openConnection();
    if (getOffer(o.getOfferId()) != null) {
      throw new SaleException("Offer id is already exist");
    }
    sqLiteDataBase.insertOfferTable(o);
    sqLiteDataBase.closeConnection();
  }

  private Currency selectCurrency(int currencyId) {
    String sql = "SELECT * FROM currency WHERE id = ?";
    conn = sqLiteDataBase.openConnection();

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, currencyId);
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
    } finally {
      sqLiteDataBase.closeConnection();
    }
    return null;
  }

  public OfferE getOffer(int offerId) {

    String sql = "SELECT * FROM offer WHERE offerId = ?";

    conn = sqLiteDataBase.openConnection();

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, offerId);
      OfferE o;
      try (ResultSet rs = pstmt.executeQuery()) {

        o = new OfferE();
        o.setOfferId(rs.getInt("offerId"));
        o.setType(rs.getString("type"));
        o.setOfferDescription(rs.getString("offerDescription"));
        o.setDiscountedPrice(rs.getDouble("discountedPrice"));
        o.setDiscountPercentage(rs.getInt("discountPercentage"));
        o.setCreateDate(rs.getDate("createDate"));
        o.setProduct(getProduct(rs.getInt("productId")));
      }

      return o;

    } catch (SQLException e) {
      logger.error(e.getMessage());
    } finally {
      sqLiteDataBase.closeConnection();
    }
    return null;
  }

  private Product getProduct(int prodId) {

    String sql = "SELECT * FROM product WHERE id = ?";
    conn = sqLiteDataBase.openConnection();
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, prodId);
      Product product;
      Currency currency;
      try (ResultSet rs = pstmt.executeQuery()) {

        product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setImage(rs.getBytes("image"));
        product.setProductInfo(rs.getString("productInfo"));
        product.setPrice(rs.getDouble("price"));
        product.setDeliveryInfo(rs.getString("deliveryInfo"));
        product.setRating(rs.getDouble("rating"));
        currency = selectCurrency(rs.getInt("currencyId"));
      }
      product.setCurrency(currency);
      return product;

    } catch (SQLException e) {
      logger.error(e.getMessage());
    } finally {
      sqLiteDataBase.closeConnection();
    }
    return null;
  }
}
