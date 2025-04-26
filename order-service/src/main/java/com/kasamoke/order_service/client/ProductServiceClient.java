package com.kasamoke.order_service.client;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.coyote.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service")
public interface ProductServiceClient {

    @PostMapping("/api/product/getProductAvailability/")
    ResponseEntity<Boolean> getProductAvailability(@RequestBody JsonNode jsonNode);

    @PostMapping("/api/product/reduceProductStock/")
    ResponseEntity<String> reduceProductStock(@RequestBody JsonNode jsonNode);
}
