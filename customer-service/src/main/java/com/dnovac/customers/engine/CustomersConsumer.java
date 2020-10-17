package com.dnovac.customers.engine;

import com.dnovac.customers.web.domain.Book;
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
   * @param book
   * @throws IOException
   */
  @KafkaListener(topics = "libraryTopic", groupId = "group_id", containerFactory = "bookKafkaListenerContainerFactory")
  public void consume(Book book) throws IOException {

    log.info(String.format("[ Customers-Service ] -> Consumed message -> %s, %s, %s, %s",
            book.getIsbn(),
            book.getAuthor(),
            book.getName(),
            book.getGenre()));
  }

}
