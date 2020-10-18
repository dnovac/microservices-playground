package com.dnovac.library.web;

import com.dnovac.library.engine.LibraryProducer;
import com.dnovac.library.service.BookService;
import com.dnovac.library.web.domain.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Dan Novac on 13/10/2020
 * @project microservices-playground
 */
@RestController
@RequestMapping("books")
@RequiredArgsConstructor
@Slf4j
public class LibraryController {

  private final BookService service;

  @PostMapping("/publish")
  public void publishBook(@RequestBody Book book) {

    service.publishBook(book);
  }

  @Cacheable(value = "books", key = "#bookId")
  @GetMapping("/{bookId}")
  public Book findOneById(@PathVariable("bookId") String bookId) {

    log.info("Searching for book with id: {}", bookId);
    return service.findOneById(Long.valueOf(bookId));
  }


}
