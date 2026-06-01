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
    private final OrderConverter orderConverter;

    public OrderRepositoryAdapter(OrderMapper orderMapper, OrderConverter orderConverter) {
        this.orderMapper = orderMapper;
        this.orderConverter = orderConverter;
    }

    @Override
    public void save(Order order) {
        OrderPersistence persistence = orderConverter.toPersistence(order);
        if (orderMapper.existById(order.getId())) {
        orderMapper.update(persistence);
    } else {
        orderMapper.insert(persistence);
    }
    }

    @Override
    public Optional<Order> findById(java.util.UUID id) {
        return orderMapper.findById(id)
            .map(orderConverter::toDomain);
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
