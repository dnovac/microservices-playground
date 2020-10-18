package com.dnovac.library.service;

import com.dnovac.library.engine.LibraryProducer;
import com.dnovac.library.repository.BookRepository;
import com.dnovac.library.web.domain.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
   * @param book
   */
  public void publishBook(Book book) {

    //publish message to topic first
    producer.publish(book);

    log.info("Saving book with isbn: {} and title: {} in Redis DB", book.getIsbn(), book.getName());
    com.dnovac.library.model.Book bookEntity = com.dnovac.library.model.Book.builder()
            .author(book.getAuthor())
            .genre(com.dnovac.library.model.Book.Genre.PSYCHOLOGICAL)
            .title(book.getName())
            .isbn(Optional.ofNullable(book.getIsbn()).map(Object::toString).orElse(UUID.randomUUID().toString()))
            .build();

    repository.save(bookEntity);
  }

  public Book findOneById(Long id) {

    Optional<com.dnovac.library.model.Book> bookEntity = repository.findById(id);
    return bookEntity.map(book -> Book.builder().isbn(Long.valueOf(book.getIsbn()))
            .genre(book.getGenre().name())
            .author(book.getAuthor())
            .name(book.getTitle()).build()).orElse(null);
  }

  public List<Book> findAll() {

    List<com.dnovac.library.model.Book> allBooks = repository.findAll();
    return allBooks.stream().map(book -> Book.builder().isbn(Long.valueOf(book.getIsbn()))
            .genre(book.getGenre().name())
            .author(book.getAuthor())
            .name(book.getTitle()).build()).collect(Collectors.toList());
  }

}
