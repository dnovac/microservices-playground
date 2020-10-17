package com.dnovac.customers.web.domain;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * @author Dan Novac on 09/10/2020
 * @project sandbox
 */
@Data
@AllArgsConstructor
public class Customer {

  private String name;
  private String age;

}
