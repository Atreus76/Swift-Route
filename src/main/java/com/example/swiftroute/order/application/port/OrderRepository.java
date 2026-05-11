package com.example.swiftroute.order.application.port;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.swiftroute.order.domain.model.Order;
import com.example.swiftroute.order.domain.model.OrderStatus;

public interface OrderRepository{
    void save(Order order);
    Optional<Order> findById(UUID id);
    List<Order> findByCustomerId(UUID customerId);
    List<Order> findByStatus(OrderStatus status);
}
