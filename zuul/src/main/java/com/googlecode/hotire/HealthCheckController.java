package com.googlecode.hotire;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/health_check")
@RestController
public class HealthCheckController {

  @GetMapping
  public ResponseEntity checkHealth() {
    return ResponseEntity.ok().build();
  }
}
