package com.dnovac.library.engine;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


/**
 * @author Dan Novac on 13/10/2020
 * @project microservices-playground
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class LibraryProducer {

  private final KafkaTemplate<String, String> kafkaTemplate;

  private final NewTopic libraryTopic;

  /**
   * @param message
   */
  public void sendMessage(String message) {

    log.info(String.format("[Library-Service] -> Producing message -> %s on topic: %s", message, libraryTopic.name()));
    ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(libraryTopic.name(), message);

    ListenableFutureCallback<SendResult<String, String>> listenableFutureCallback = getListenableFutureCallback(message);
    future.addCallback(listenableFutureCallback);
  }

  /**
   * @param message
   * @return future callback
   * @apiNote The send API returns a ListenableFuture object.
   * If we want to block the sending thread and get the result about the sent message, we can call the get API of the ListenableFuture object.
   * The thread will wait for the result, but it will slow down the producer.
   * Kafka is a fast stream processing platform.
   * So it's a better idea to handle the results asynchronously so that the subsequent messages do not wait for the result of the previous message.
   */
  private ListenableFutureCallback<SendResult<String, String>> getListenableFutureCallback(String message) {

    return new ListenableFutureCallback<SendResult<String, String>>() {
      @Override
      public void onFailure(Throwable throwable) {

        log.error("Unable to send message: {} due to: {}", message, throwable.getMessage());
      }

      @Override
      public void onSuccess(SendResult<String, String> stringStringSendResult) {

        log.info("Message sent: {} with offset: {}", message, stringStringSendResult.getRecordMetadata().offset());
      }
    };
  }

}
