package com.shop.sales;

import com.google.gson.Gson;
import com.shop.sales.bean.ProductOffer;
import com.shop.sales.controller.ProductSales;
import com.shop.sales.entity.Currency;
import com.shop.sales.service.impl.ProductOfferServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductSalesTests {

  @Autowired MockMvc mockMvc;

  @Autowired Environment environment;

  MappingJackson2HttpMessageConverter jackson2HttpMessageConverter;

  @Autowired private ProductOfferServiceImpl productOfferServiceImpl;

  @Before
  public void setUp() {

    MockitoAnnotations.initMocks(this);
    ProductSales productSales = new ProductSales(productOfferServiceImpl);
    mockMvc =
        MockMvcBuilders.standaloneSetup(productSales)
            .setMessageConverters(jackson2HttpMessageConverter)
            .build();
  }

  @Test
  public void given_correct_uri_then_return_success_ok_response() throws Exception {
    // Act & Assert
    mockMvc.perform(get("/shopping/offer/1")).andExpect(status().isOk());
  }

  @Test
  public void given_correct_uri_but_offer_expired_then_return_error_response() throws Exception {
    // Act & Assert
    mockMvc
        .perform(get("/shopping/offer/3"))
        .andExpect(status().is(403))
        .andExpect(content().string(CoreMatchers.anything("Offer has been expired.")));
  }

  @Test
  public void given_wrong_uri_then_return_error_404_response() throws Exception {
    // Act & Assert
    mockMvc.perform(get("/shopping/wrong/uri/1")).andExpect(status().is4xxClientError());
  }

  @Test
  public void given_correct_uri_when_incomplete_path_variable_then_return_error() throws Exception {
    // Act & Assert
    mockMvc.perform(get("/shopping/offer/abc")).andExpect(status().is4xxClientError());
  }

  @Test
  public void given_correct_uri_for_createOffer_then_return_success_ok_response() throws Exception {

    ProductOffer productOffer = new ProductOffer();
    productOffer.setTermsAndConditions("abd");
    productOffer.setDescription("desc");
    productOffer.setVat(10);
    productOffer.setDiscountPercentage(20);
    productOffer.setProdId(1);
    productOffer.setName("DELL laptop");
    productOffer.setPrice(700);
    productOffer.setCurrency(new Currency("British Pound", "£"));
    Gson gson = new Gson();
    String po = gson.toJson(productOffer);

    // Act & Assert
    mockMvc
        .perform(post("/shopping/offer").contentType(MediaType.APPLICATION_JSON).content(po))
        .andExpect(status().isOk());
  }

  @Test
  public void given_wrong_for_createOffer_uri_then_return_error_404_response() throws Exception {
    // Act & Assert
    mockMvc.perform(post("/shopping/wrong/url/offer")).andExpect(status().is4xxClientError());
  }

  @Test
  public void given_correct_uri_for_createOffer_when_incomplete_path_variable_then_return_error()
      throws Exception {

    Gson gson = new Gson();
    String dso = gson.toJson(null);

    // Act & Assert
    mockMvc
        .perform(post("/shopping/offer").contentType(MediaType.APPLICATION_JSON).content(dso))
        .andExpect(status().is4xxClientError());
  }
}
