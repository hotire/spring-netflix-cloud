package web.service;

import feign.Request;
import feign.Response;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class DefaultErrorDecoderTest {

    @Test
    void decode() {
        // given
        final Request request = Request.create(Request.HttpMethod.GET, "", Map.of(), Request.Body.empty());
        final Response response = Response.builder()
                                          .body("", Charset.defaultCharset())
                                          .headers(Map.of()).reason("")
                                          .status(404)
                                          .request(request)
                                          .build();
        final DefaultErrorDecoder defaultErrorDecoder = new DefaultErrorDecoder();

        // when
        final Exception result = defaultErrorDecoder.decode("", response);

        // then
        assertThat(result).isInstanceOf(FeignWrapperException.class);
    }

}