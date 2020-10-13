package com.dnovac.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


/**
 * @author Dan Novac on 13/10/2020
 * @project microservices-playground
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class LibraryServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(LibraryServiceApplication.class, args);
  }
}
