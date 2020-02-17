package web.service;

import feign.Response;
import feign.codec.ErrorDecoder;

public class DefaultErrorDecoder extends ErrorDecoder.Default {
    @Override
    public Exception decode(String methodKey, Response response) {
        return super.decode(methodKey, response);
    }
}
