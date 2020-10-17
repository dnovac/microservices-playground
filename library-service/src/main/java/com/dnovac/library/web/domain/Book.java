package com.dnovac.library.web.domain;

import lombok.Builder;
import lombok.Data;


/**
 * @author Dan Novac on 14/10/2020
 * @project microservices-playground
 */
@Data
@Builder
public class Book {

  private Long isbn;
  private String name;
  private String genre;
  private String author;

}
