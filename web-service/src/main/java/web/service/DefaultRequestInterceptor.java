package web.service;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class DefaultRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("default", "config");
    }
}
