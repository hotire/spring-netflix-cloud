package web.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class FeignConfigTest {

    @Test
    void config() {
        // given
        FeignConfig feignConfig = new FeignConfig();

        // when
        final var decoder = feignConfig.defaultErrorDecoder();
        final var interceptor = feignConfig.defaultRequestInterceptor();

        // then
        assertThat(decoder).isNotNull();
        Assertions.assertThat(interceptor).isNotNull();
    }
}