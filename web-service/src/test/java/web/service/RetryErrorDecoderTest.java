package web.service;

import feign.*;
import feign.slf4j.Slf4jLogger;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.http.HttpStatus;

import java.nio.charset.Charset;
import java.util.Map;

import static feign.Logger.Level.FULL;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class RetryErrorDecoderTest {

    private static class Sender {
        private void send(){};
    };

    @Test
    void retry() {
        // given
        final Sender sender = mock(Sender.class);
        final Retryer retryer = new Retryer.Default(1000, 2000, 3);
        final int status = HttpStatus.NOT_FOUND.value();
        final Client client = (request, options) -> {
            sender.send();
            return Response.builder().body("", Charset.defaultCharset()).request(request).status(status).headers(Map.of()).reason("").build();
        };

        // when
        assertThrows(RetryableException.class, () -> Feign.builder()
                                                          .retryer(retryer)
                                                          .client(client)
                                                          .contract(new SpringMvcContract())
                                                          .errorDecoder(new RetryErrorDecoder())
                                                          .logger(new Slf4jLogger())
                                                          .logLevel(FULL)
                                                          .target(TestClient.class, "http://localhost:8080")
                                                          .findById(""));
        // then
        verify(sender, times(3)).send();
    }
}