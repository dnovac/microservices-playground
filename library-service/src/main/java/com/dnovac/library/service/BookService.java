package com.dnovac.library.service;

import com.dnovac.library.engine.LibraryProducer;
import com.dnovac.library.model.Book;
import com.dnovac.library.repository.BookRepository;
import com.dnovac.library.web.domain.BookDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * @author Dan Novac on 18/10/2020
 * @project microservices-playground
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {

  private final LibraryProducer producer;

  private final BookRepository repository;

  /**
   * Publish book to kafka topic and save into redis in-memory database
   *
   * @param bookDTO
   */
  @HystrixCommand(fallbackMethod = "defaultPublishFallback")
  public void publishBook(BookDTO bookDTO) {

    //publish message to topic first
    producer.publish(bookDTO);

    log.info("Saving book with isbn: {} and title: {} in Redis DB", bookDTO.getIsbn(), bookDTO.getName());
    Book bookEntity = Book.builder()
            .author(bookDTO.getAuthor())
            .genre(Book.Genre.PSYCHOLOGICAL) //todo un-harcode
            .title(bookDTO.getName())
            .isbn(Optional.ofNullable(bookDTO.getIsbn()).map(Object::toString).orElse(UUID.randomUUID().toString()))
            .build();

    repository.save(bookEntity);
  }

  public BookDTO findOneById(Long id) {

    Optional<Book> bookEntity = repository.findById(id);
    return bookEntity.map(book -> BookDTO.builder().isbn(Long.valueOf(book.getIsbn()))
            .genre(book.getGenre().name())
            .author(book.getAuthor())
            .name(book.getTitle()).build()).orElse(null);
  }

  public List<BookDTO> findAll() {

    List<Book> allBooks = repository.findAll();
    return allBooks.stream().map(book -> BookDTO.builder().isbn(Long.valueOf(book.getIsbn()))
            .genre(book.getGenre().name())
            .author(book.getAuthor())
            .name(book.getTitle()).build()).collect(Collectors.toList());
  }

  private void defaultPublishFallback() {
    log.warn("Something goes wrong! No book published!");
  }

}
