package com.googlecode.hotire;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class SampleController {

  @GetMapping
  public String hello() {
    return "hello";
  }
}
