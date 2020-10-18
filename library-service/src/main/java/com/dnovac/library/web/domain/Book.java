package com.dnovac.library.web.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


/**
 * @author Dan Novac on 14/10/2020
 * @project microservices-playground
 */
@Data
@Builder
public class Book implements Serializable {

  private Long isbn;
  private String name;
  private String genre;
  private String author;

}
