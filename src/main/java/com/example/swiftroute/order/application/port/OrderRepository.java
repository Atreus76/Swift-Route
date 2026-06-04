package com.example.swiftroute.order.application.port;

import java.util.Optional;
import java.util.UUID;

import com.example.swiftroute.order.domain.model.Order;
import com.example.swiftroute.order.domain.model.OrderStatus;
import com.example.swiftroute.shared.domain.Page;
import com.example.swiftroute.shared.domain.PageRequest;

public interface OrderRepository{
    void save(Order order);
    Optional<Order> findById(UUID id);
    Page<Order> findByCustomerId(UUID customerId, PageRequest pageRequest);
    Page<Order> findByStatus(OrderStatus status, PageRequest pageRequest);
}
