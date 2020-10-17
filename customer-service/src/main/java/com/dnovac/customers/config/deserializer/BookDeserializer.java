package com.dnovac.customers.config.deserializer;

import com.dnovac.customers.web.domain.Book;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;


/**
 * @author Dan Novac on 17/10/2020
 * @project microservices-playground
 */
public class BookDeserializer implements KafkaDeserializer<Book> {

  @Override
  public void configure(Map<String, ?> var1, boolean var2) {

  }

  @Override
  public Book deserialize(String var1, byte[] var2) {

    ObjectMapper mapper = new ObjectMapper();
    Book book = null;
    try {
      book = mapper.readValue(var2, Book.class);
    } catch (Exception e) {

      e.printStackTrace();
    }
    return book;
  }

  @Override
  public void close() {

  }

}
