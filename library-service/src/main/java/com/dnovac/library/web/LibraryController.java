package com.dnovac.library.web;

import com.dnovac.library.service.BookService;
import com.dnovac.library.web.domain.BookDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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

  @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
  public List<BookDTO> findAll() {

    return service.findAll();
  }

  @PostMapping("/publish")
  public void publishBook(@RequestBody BookDTO bookDTO) {

    service.publishBook(bookDTO);
  }

  @Cacheable(value = "books", key = "#bookId")
  @GetMapping("/{bookId}")
  public BookDTO findOneById(@PathVariable("bookId") String bookId) {

    log.info("Searching for book with id: {}", bookId);
    return service.findOneById(Long.valueOf(bookId));
  }

  //  FOR PUT request: @CachePut(value = "books", key = "#book.id")

}
