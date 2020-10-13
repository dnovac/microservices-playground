package com.dnovac.library.config.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Dan Novac on 09/10/2020
 * @project sandbox
 */
@Configuration
public class KafkaTopicConfig {

  @Value(value = "${spring.kafka.topic.bootstrap-servers}")
  private String bootstrapAddress;

  @Value(value = "${spring.kafka.topic.library-topic-name}")
  private String libraryTopicName;


  @Bean
  public KafkaAdmin kafkaAdmin() {
    Map<String, Object> configs = new HashMap<>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    return new KafkaAdmin(configs);
  }

  @Bean
  public NewTopic libraryTopic() {
    return new NewTopic(libraryTopicName, 1, (short) 1);
  }


}
