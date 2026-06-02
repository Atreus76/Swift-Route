package com.example.swiftroute.order.application.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.swiftroute.dispatch.domain.model.RouteStopCompletedEvent;
import com.example.swiftroute.order.application.port.OrderRepository;
import com.example.swiftroute.order.domain.model.Order;
import com.example.swiftroute.order.domain.model.OrderCancelledEvent;
import com.example.swiftroute.order.domain.model.OrderConfirmedEvent;
import com.example.swiftroute.order.domain.model.OrderDeliveredEvent;
import com.example.swiftroute.order.domain.model.OrderLine;
import com.example.swiftroute.order.domain.model.OrderPlacedEvent;
import com.example.swiftroute.order.domain.valueObject.DeliveryAddress;
import com.example.swiftroute.shared.EntityNotFoundException;
@Service
public class OrderApplicationService {
    private final OrderRepository orderRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public OrderApplicationService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Order placeOrder(UUID customerId, DeliveryAddress deliveryAddress) {
        Order order = Order.place(customerId, deliveryAddress);
        orderRepository.save(order);
        eventPublisher.publishEvent(new OrderPlacedEvent(order.getId(), customerId, Instant.now()));
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
        eventPublisher.publishEvent(new OrderConfirmedEvent(orderId, Instant.now()));
    }

    @Transactional
    public void cancelOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
            () -> EntityNotFoundException.of("Order", orderId));
        order.cancel();
        orderRepository.save(order);
        eventPublisher.publishEvent(new OrderCancelledEvent(orderId, null, Instant.now()));
    }

    @Transactional
    public Order getOrder(UUID orderId) {
        return orderRepository.findById(orderId).orElseThrow(
            () -> EntityNotFoundException.of("Order", orderId));
    }

    @Transactional
    public void markDelivered(UUID orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(
            () -> EntityNotFoundException.of("Order", orderId));
        order.markDelivered();
        orderRepository.save(order);
        eventPublisher.publishEvent(new OrderDeliveredEvent(orderId, Instant.now()));
    }

    // @EventListener
    // public void on(RouteStopCompletedEvent event){
    //     markDelivered(event.orderId());
    // }


}
