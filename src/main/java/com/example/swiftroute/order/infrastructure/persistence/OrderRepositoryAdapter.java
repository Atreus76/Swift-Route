package com.example.swiftroute.order.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.swiftroute.order.application.port.OrderRepository;
import com.example.swiftroute.order.domain.model.Order;
import com.example.swiftroute.order.domain.model.OrderStatus;

@Repository
public class OrderRepositoryAdapter implements OrderRepository{
    private final OrderMapper orderMapper;

    public OrderRepositoryAdapter(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public void save(Order order) {
        if (orderMapper.existById(order.getId())) {
    orderMapper.update(order);
    } else {
        orderMapper.insert(order);
    }
    }

    @Override
    public Optional<Order> findById(java.util.UUID id) {
        return orderMapper.findById(id);
    }

    @Override
    public List<Order> findByCustomerId(UUID customerId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByCustomerId'");
    }

    @Override
    public List<Order> findByStatus(OrderStatus status) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByStatus'");
    }
}
