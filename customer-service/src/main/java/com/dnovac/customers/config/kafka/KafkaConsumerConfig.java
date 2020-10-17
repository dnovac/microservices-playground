package com.dnovac.customers.config.kafka;

import com.dnovac.customers.config.deserializer.BookDeserializer;
import com.dnovac.customers.web.domain.Book;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Dan Novac on 11/10/2020
 * @project sandbox
 */
@EnableKafka
@Configuration
public class KafkaConsumerConfig {

  @Value(value = "${spring.kafka.consumer.bootstrap-servers}")
  private String bootstrapAddress;

  @Value(value = "${spring.kafka.consumer.group-id}")
  private String groupId;

  @Bean
  public ConsumerFactory<String, Book> bookConsumerFactory() {

    JsonDeserializer<Book> deserializer = new JsonDeserializer<>(Book.class);
    deserializer.setRemoveTypeHeaders(false);
    deserializer.addTrustedPackages("com.dnovac.customers.web.domain");
    deserializer.setUseTypeMapperForKey(true);

    Map<String, Object> props = new HashMap<>();
    props.put(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
            bootstrapAddress);
    props.put(
            ConsumerConfig.GROUP_ID_CONFIG,
            groupId);
    props.put(
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
            StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
            deserializer);

    return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Book> bookKafkaListenerContainerFactory() {

    ConcurrentKafkaListenerContainerFactory<String, Book> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(bookConsumerFactory());
    return factory;
  }

  /**
   * A filter just for example
   *
   * @return ConcurrentKafkaListenerContainerFactory
   */
  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Book> filterKafkaListenerContainerFactory() {

    ConcurrentKafkaListenerContainerFactory<String, Book> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(bookConsumerFactory());
    factory.setRecordFilterStrategy(
            record -> record.value().getGenre().equals("Thriller"));
    return factory;
  }

}
