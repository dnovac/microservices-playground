package com.dnovac.library.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;


/**
 * @author Dan Novac on 18/10/2020
 * @project microservices-playground
 */
@Data
@Builder
@RedisHash("Book")
public class Book implements Serializable {

  public enum Genre {
    THRILLER,
    FANTASY,
    PSYCHOLOGICAL,
    SCIENCE
  }

  @Id
  @SequenceGenerator(name = "SEQ_GEN", sequenceName = "SEQ_BOOK", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
  private Long id;
  private String isbn;
  private String author;
  private String title;
  private Genre genre;

}
