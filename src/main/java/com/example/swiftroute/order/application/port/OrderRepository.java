package com.example.swiftroute.order.application.port;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.swiftroute.order.domain.model.Order;
import com.example.swiftroute.order.domain.model.OrderStatus;
import com.example.swiftroute.order.infrastructure.persistence.OrderPersistence;

public interface OrderRepository{
    void save(Order order);
    Optional<OrderPersistence> findById(UUID id);
    List<Order> findByCustomerId(UUID customerId);
    List<Order> findByStatus(OrderStatus status);
}
