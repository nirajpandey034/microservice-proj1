package com.kasamoke.product_service.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.kasamoke.product_service.model.ProductModel;
import com.kasamoke.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping({"/create", "/create/"})
    private ResponseEntity<String> createUser(@RequestBody ProductModel productModel) {
        try{
            productService.createProduct(productModel);
            return ResponseEntity.status(200).body("Product added");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Some error Occurred" + e.getMessage());
        }
    }

    @PostMapping({"/getProductAvailability", "/getProductAvailability/"})
    private ResponseEntity<Boolean> checkAvailability(@RequestBody JsonNode jsonNode) {
        try {
            Integer productId = jsonNode.get("id").asInt();
            Integer quantity = jsonNode.get("quantity").asInt();

            Boolean isAvailable = productService.isProductAvailable(productId, quantity);

            return isAvailable ? ResponseEntity.status(200).body(true) : ResponseEntity.status(200).body(false);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(false);
        }
    }

    @PostMapping({"/reduceProductStock", "/reduceProductStock/"})
    private ResponseEntity<String> reduceQuantity(@RequestBody JsonNode jsonNode) {
        try {
            Integer productId = jsonNode.get("id").asInt();
            Integer quantity = jsonNode.get("quantity").asInt();

             productService.decreaseProductQuantity(productId, quantity);
             return ResponseEntity.ok("Quantity reduced by: " + quantity);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Some Error Occurred" + e.getMessage());
        }
    }
}
