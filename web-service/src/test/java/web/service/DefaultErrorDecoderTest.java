package web.service;

import feign.Client;
import feign.Feign;
import feign.Request;
import feign.Response;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.http.HttpStatus;

import java.nio.charset.Charset;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void verifyDefaultErrorDecoder() {
        final int status = HttpStatus.NOT_FOUND.value();
        final Client client = (request, options) -> Response.builder().body("", Charset.defaultCharset()).request(request).status(status).headers(Map.of()).reason("").build();

        final TestClient testClient = Feign.builder()
                                           .client(client)
                                           .contract(new SpringMvcContract())
                                           .errorDecoder(new DefaultErrorDecoder())
                                           .target(TestClient.class, "http://localhost:8080");

        assertThrows(FeignWrapperException.class, () -> testClient.findById("hotire"));
    }
}