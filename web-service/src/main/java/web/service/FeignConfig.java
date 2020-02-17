package web.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public DefaultRequestInterceptor defaultRequestInterceptor() {
        return new DefaultRequestInterceptor();
    }

}
