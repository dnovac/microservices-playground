package com.dnovac.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author Dan Novac on 13/10/2020
 * @project microservices-playground
 */
@EnableDiscoveryClient
@EnableCaching
@EnableCircuitBreaker
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class LibraryServiceApplication {

  public static void main(String[] args) {

    SpringApplication.run(LibraryServiceApplication.class, args);
  }


}
