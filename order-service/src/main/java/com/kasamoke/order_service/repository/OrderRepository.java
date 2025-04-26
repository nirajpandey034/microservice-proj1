package com.kasamoke.order_service.repository;


import com.kasamoke.order_service.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel, Integer> {
}
