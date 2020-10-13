package com.dnovac.customers.engine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * @author Dan Novac on 09/10/2020
 * @project sandbox
 */
@Slf4j
@Service
public class CustomersConsumer {

  /**
   * Multiple listeners can be implemented for a topic, each with a different group Id.
   * Furthermore, one consumer can listen for messages from various topics:
   * ex: @KafkaListener(topics = "topic1, topic2", groupId = "foo", containerFactory="filterKafkaListenerContainerFactory")
   *
   * @param message
   * @throws IOException
   */
  @KafkaListener(topics = "libraryTopic", groupId = "group_id")
  public void consume(String message) throws IOException {

    log.info(String.format("[ Customers-Service ] -> Consumed message -> %s", message));
  }

}
