package com.dnovac.library.engine;

import com.dnovac.library.web.domain.BookDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
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

  private final KafkaTemplate<String, BookDTO> bookKafkaTemplate;

  private final NewTopic bookTopic;

  /**
   * @param bookDTO
   */
  public void publish(BookDTO bookDTO) {

    log.info(String.format("[Library-Service] -> Publishing Book -> %s on topic: %s", bookDTO.getIsbn(), bookTopic.name()));
    /*Book book = Book.builder()
            .name("Brothers Karamazov")
            .author("F.Dostoievski")
            .genre("Classics/Thriller")
            .isbn(123456L)
            .build();*/
    ListenableFuture<SendResult<String, BookDTO>> future = bookKafkaTemplate.send(bookTopic.name(), bookDTO);

    ListenableFutureCallback<SendResult<String, BookDTO>> listenableFutureCallback = getListenableFutureCallback(bookDTO);
    future.addCallback(listenableFutureCallback);
  }

  /**
   * @param bookDTO
   * @return future callback
   * @apiNote The send API returns a ListenableFuture object.
   * If we want to block the sending thread and get the result about the sent message, we can call the get API of the ListenableFuture object.
   * The thread will wait for the result, but it will slow down the producer.
   * Kafka is a fast stream processing platform.
   * So it's a better idea to handle the results asynchronously so that the subsequent messages do not wait for the result of the previous message.
   */
  private ListenableFutureCallback<SendResult<String, BookDTO>> getListenableFutureCallback(BookDTO bookDTO) {

    return new ListenableFutureCallback<>() {
      @Override
      public void onFailure(Throwable throwable) {

        log.error("Unable to publish book with isbn: {} due to: {}", bookDTO.getIsbn(), throwable.getMessage());
      }

      @Override
      public void onSuccess(SendResult<String, BookDTO> stringStringSendResult) {

        log.info("Book published with ISBN: {} with offset: {}", bookDTO.getIsbn(),
                stringStringSendResult.getRecordMetadata().offset());
      }
    };
  }

}
