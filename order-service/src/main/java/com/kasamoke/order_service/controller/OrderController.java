package com.kasamoke.order_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kasamoke.order_service.client.ProductServiceClient;
import com.kasamoke.order_service.model.OrderModel;
import com.kasamoke.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductServiceClient productServiceClient;

    @PostMapping({"/create", "/create/"})
    public ResponseEntity<String> createOrder(@RequestBody OrderModel orderModel) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode requestBody = objectMapper.createObjectNode();
            requestBody.put("id", orderModel.getProduct_id());
            requestBody.put("quantity", orderModel.getProduct_quantity());

            if (Boolean.TRUE.equals(productServiceClient.getProductAvailability(requestBody).getBody())) {
                OrderModel newOrder = orderService.createOrder(orderModel);
                productServiceClient.reduceProductStock(requestBody).getBody();
                return ResponseEntity.status(200).body("Order Created: " + newOrder.getOrder_id());
            }
            else
                return ResponseEntity.status(500).body("This much stock is not there");
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Some Error Occurred" + e.getMessage());
        }
    }

}
