package com.dnovac.customers.config.deserializer;

import com.dnovac.customers.web.domain.Book;

import java.io.Closeable;
import java.util.Map;


/**
 * @author Dan Novac on 17/10/2020
 * @project microservices-playground
 */
public interface KafkaDeserializer<T> extends Closeable {

  void configure(Map<String, ?> var1, boolean var2);

  T deserialize(String var1, byte[] var2);

  void close();

}
