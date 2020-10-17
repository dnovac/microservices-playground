package com.dnovac.library.config.serializer;

import com.dnovac.library.web.domain.Book;

import java.io.Closeable;
import java.util.Map;


/**
 * @author Dan Novac on 17/10/2020
 * @project microservices-playground
 */
public interface KafkaSerializer<T> extends Closeable {

  void configure(Map<String, ?> var1, boolean var2);

  byte[] serialize(String var1, T var2);

  void close();

}
