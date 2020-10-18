package com.dnovac.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * @author Dan Novac on 13/10/2020
 * @project microservices-playground
 */
@EnableDiscoveryClient
@EnableCaching
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class LibraryServiceApplication {

  public static void main(String[] args) {

    SpringApplication.run(LibraryServiceApplication.class, args);
  }


}
