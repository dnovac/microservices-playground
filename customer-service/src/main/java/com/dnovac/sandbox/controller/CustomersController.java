package com.dnovac.sandbox.controller;

import com.dnovac.sandbox.engine.CustomersProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Dan Novac on 09/10/2020
 * @project sandbox
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("customers")
public class CustomersController {

  private final CustomersProducer producer;

  @PostMapping("publish")
  public void sendMessage(@RequestParam("message") String message) {
    producer.sendMessage(message);
  }




}
