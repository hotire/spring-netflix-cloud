package web.service;

import feign.Request;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

import java.util.Date;

public class RetryErrorDecoder extends ErrorDecoder.Default {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (HttpStatus.valueOf(response.status()).isError()) {
            final Request request = response.request();
            return new RetryableException(response.status(), "", request.httpMethod(), new Date(), request);
        }
        return super.decode(methodKey, response);
    }
}
