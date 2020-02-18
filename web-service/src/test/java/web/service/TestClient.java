package web.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface TestClient {
    @GetMapping("{id}")
    String findById(@PathVariable String id);
}
