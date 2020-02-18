package web.service;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class DefaultErrorDecoder extends ErrorDecoder.Default {
    @Override
    public Exception decode(String methodKey, Response response) {
        Exception exception = super.decode(methodKey, response);

        if (exception instanceof FeignException) {
            exception = new FeignWrapperException((FeignException) exception, response, methodKey);
        }

        return exception;
    }
}
