package com.shop.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SalesApplication {

  public static void main(String[] args) {
    SpringApplication.run(SalesApplication.class, args);
  }

  @Bean(name = DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
  public DispatcherServlet dispatcherServlet() {

    DispatcherServlet dispatcherServlet = new DispatcherServlet();
    dispatcherServlet.setDetectAllHandlerMappings(true);
    dispatcherServlet.setDispatchOptionsRequest(true);
    return dispatcherServlet;
  }
}
