package web.service;

import feign.FeignException;
import feign.Response;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FeignWrapperException extends RuntimeException {
    private final FeignException feignException;
    private final Response response;
    private final String methodKey;
}
