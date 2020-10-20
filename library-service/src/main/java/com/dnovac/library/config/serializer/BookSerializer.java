package com.dnovac.library.config.serializer;

import com.dnovac.library.web.domain.BookDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;


/**
 * @author Dan Novac on 17/10/2020
 * @project microservices-playground
 */
public class BookSerializer implements KafkaSerializer<BookDTO>{

  @Override
  public void configure(Map<String, ?> var1, boolean var2) {

  }

  @Override
  public byte[] serialize(String var1, BookDTO var2) {
    byte[] retVal = null;
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      retVal = objectMapper.writeValueAsString(var2).getBytes();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return retVal;
  }

  @Override
  public void close() {

  }

}
