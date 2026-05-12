package com.example.swiftroute.order.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.swiftroute.order.application.port.OrderRepository;
import com.example.swiftroute.order.domain.model.Order;
import com.example.swiftroute.order.domain.model.OrderLine;
import com.example.swiftroute.order.domain.valueObject.DeliveryAddress;
import com.example.swiftroute.shared.EntityNotFoundException;
@Service
public class OrderApplicationService {
    private final OrderRepository orderRepository;

    public OrderApplicationService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Order placeOrder(UUID customerId, DeliveryAddress deliveryAddress) {
        Order order = Order.place(customerId, deliveryAddress);
        orderRepository.save(order);
        return order;
    }

    @Transactional
    public void addLine(UUID orderId, OrderLine orderLine){
        Order order = orderRepository.findById(orderId).orElseThrow(
            () -> EntityNotFoundException.of("Order", orderId));
        order.addLine(orderLine);
        orderRepository.save(order);
    }

    @Transactional
    public void confirmOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
            () -> EntityNotFoundException.of("Order", orderId));
        order.confirm();
        orderRepository.save(order);
    }

    @Transactional
    public void cancelOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
            () -> EntityNotFoundException.of("Order", orderId));
        order.cancel();
        orderRepository.save(order);
    }

    @Transactional
    public Order getOrder(UUID orderId) {
        return orderRepository.findById(orderId).orElseThrow(
            () -> EntityNotFoundException.of("Order", orderId));
    }



}
