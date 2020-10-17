package com.dnovac.customers.web.domain;

import lombok.Builder;
import lombok.Data;


/**
 * @author Dan Novac on 14/10/2020
 * @project microservices-playground
 */
@Data
public class Book {

  private Long isbn;
  private String name;
  private String genre;
  private String author;

}
