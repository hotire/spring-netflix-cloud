package com.googlecode.hotire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * https://github.com/Netflix/Hystrix/wiki/How-To-Use
 */
@EnableZuulProxy
@SpringBootApplication
public class ZuulApplication {
  public static void main(String[] args) {
    SpringApplication.run(ZuulApplication.class, args);
  }
}
