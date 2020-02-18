package web.service;

import feign.RequestTemplate;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class DefaultRequestInterceptorTest {
    @Test
    void apply() {
        // given
        final RequestTemplate requestTemplate = new RequestTemplate();
        final DefaultRequestInterceptor defaultRequestInterceptor = new DefaultRequestInterceptor();

        // when
        defaultRequestInterceptor.apply(requestTemplate);
        final Map<String, Collection<String>> result = requestTemplate.headers();

        // then
        assertThat(result).containsKey("default");
    }
}