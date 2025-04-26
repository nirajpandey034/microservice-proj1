package com.kasamoke.order_service.service;

import com.kasamoke.order_service.model.OrderModel;
import com.kasamoke.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public OrderModel createOrder(OrderModel orderModel) {
        try {
            return orderRepository.save(orderModel);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
