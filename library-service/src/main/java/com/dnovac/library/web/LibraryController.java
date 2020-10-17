package com.dnovac.library.web;

import com.dnovac.library.engine.LibraryProducer;
import com.dnovac.library.web.domain.Book;
import lombok.RequiredArgsConstructor;
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
public class LibraryController {

  private final LibraryProducer producer;

  @PostMapping("/publish")
  public void addBook(@RequestBody Book book) {
    producer.publish(book);
  }


}
