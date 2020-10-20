package com.dnovac.library.config.kafka;

import com.dnovac.library.web.domain.BookDTO;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Dan Novac on 09/10/2020
 * @project sandbox
 */
@Configuration
public class KafkaProducerConfig {

  @Value(value = "${spring.kafka.producer.bootstrap-servers}")
  private String bootstrapAddress;

  @Bean
  public ProducerFactory<String, BookDTO> bookProducerFactory() {

    JsonSerializer<BookDTO> bookJsonSerializer = new JsonSerializer<>();
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
            bootstrapAddress);
    configProps.put(
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
            StringSerializer.class);
    configProps.put(
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
           bookJsonSerializer);
    return new DefaultKafkaProducerFactory<>(configProps, new StringSerializer(), bookJsonSerializer);
  }

  @Bean
  public KafkaTemplate<String, BookDTO> bookKafkaTemplate() {

    return new KafkaTemplate<>(bookProducerFactory());
  }

}
