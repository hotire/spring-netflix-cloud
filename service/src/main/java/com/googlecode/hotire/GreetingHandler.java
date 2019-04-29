package com.googlecode.hotire;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class GreetingHandler {
  public Mono<ServerResponse> hello(ServerRequest request) {
    log.info("hello");
    return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
      .body(BodyInserters.fromObject("Hello, Spring!"));
  }
}
