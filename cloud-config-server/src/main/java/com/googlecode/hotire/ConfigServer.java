package com.googlecode.hotire;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * https://www.baeldung.com/spring-cloud-configuration
 */
@EnableConfigServer
public class ConfigServer {

    public static void main(String[] arguments) {
        SpringApplication.run(ConfigServer.class, arguments);
    }
}
