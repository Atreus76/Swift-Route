package com.example.swiftroute.order.infrastructure.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.swiftroute.BaseIntegrationTest;
import com.example.swiftroute.order.application.port.OrderRepository;
import com.example.swiftroute.order.domain.model.Order;
import com.example.swiftroute.order.domain.model.OrderLine;
import com.example.swiftroute.order.domain.model.OrderStatus;
import com.example.swiftroute.order.domain.valueObject.DeliveryAddress;
import com.example.swiftroute.shared.domain.Page;
import com.example.swiftroute.shared.domain.PageRequest;

public class OrderRepositoryIntegrationTest extends BaseIntegrationTest {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    void save_shouldPersistOrder() {
        Order order = newOrder();

        orderRepository.save(order);

        Optional<Order> savedOrder = orderRepository.findById(order.getId());
        assertTrue(savedOrder.isPresent());
        assertOrderEquals(order, savedOrder.get());
    }

    @Test
    void findById_shouldReturnOrder() {
        Order order = newOrder();
        orderRepository.save(order);

        Optional<Order> foundOrder = orderRepository.findById(order.getId());

        assertTrue(foundOrder.isPresent());
        assertOrderEquals(order, foundOrder.get());
    }

    @Test
    void findById_shouldReturnOrderWithLines() {
        Order order = newOrder();
        OrderLine line = OrderLine.of(
                order.getId(),
                "SKU-001",
                "Laptop stand",
                2,
                new BigDecimal("1.250"),
                new BigDecimal("0.0100"),
                new BigDecimal("25.50"));
        order.addLine(line);
        orderRepository.save(order);

        Optional<Order> foundOrder = orderRepository.findById(order.getId());

        assertTrue(foundOrder.isPresent());
        assertOrderEquals(order, foundOrder.get());
        assertEquals(1, foundOrder.get().getLines().size());
        assertOrderLineEquals(line, foundOrder.get().getLines().get(0));
    }

    @Test
    void findByStatus_shouldReturnPaginatedResults() {
        Page<Order> before = orderRepository.findByStatus(OrderStatus.CONFIRMED, new PageRequest(0, 100));
        Order firstOrder = confirmedOrder();
        Order secondOrder = confirmedOrder();
        Order thirdOrder = confirmedOrder();
        orderRepository.save(firstOrder);
        orderRepository.save(secondOrder);
        orderRepository.save(thirdOrder);

        Page<Order> foundOrders = orderRepository.findByStatus(OrderStatus.CONFIRMED, new PageRequest(0, 100));

        assertEquals(before.getTotalElements() + 3, foundOrders.getTotalElements());
        assertEquals(0, foundOrders.getPage());
        assertEquals(100, foundOrders.getSize());
        assertTrue(foundOrders.getContent().stream().anyMatch(order -> order.getId().equals(firstOrder.getId())));
        assertTrue(foundOrders.getContent().stream().anyMatch(order -> order.getId().equals(secondOrder.getId())));
        assertTrue(foundOrders.getContent().stream().anyMatch(order -> order.getId().equals(thirdOrder.getId())));
    }

    @Test
    void save_shouldUpdateStatusOnExistingOrder() {
        Order order = newOrder();
        orderRepository.save(order);
        order.confirm();

        orderRepository.save(order);

        Optional<Order> updatedOrder = orderRepository.findById(order.getId());
        assertTrue(updatedOrder.isPresent());
        assertEquals(OrderStatus.CONFIRMED, updatedOrder.get().getStatus());
        assertEquals(order.getId(), updatedOrder.get().getId());
        assertEquals(order.getCustomerId(), updatedOrder.get().getCustomerId());
    }

    private Order newOrder() {
        return Order.place(
                UUID.randomUUID(),
                DeliveryAddress.of("123 Main St", "Hanoi", "100000", "Vietnam"));
    }

    private Order confirmedOrder() {
        Order order = newOrder();
        order.confirm();
        return order;
    }

    private void assertOrderEquals(Order expected, Order actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getCustomerId(), actual.getCustomerId());
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getDeliveryAddress(), actual.getDeliveryAddress());
        assertEquals(expected.getLines().size(), actual.getLines().size());
    }

    private void assertOrderLineEquals(OrderLine expected, OrderLine actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getOrderId(), actual.getOrderId());
        assertEquals(expected.getSku(), actual.getSku());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getQuantity(), actual.getQuantity());
        assertEquals(0, expected.getWeightKg().compareTo(actual.getWeightKg()));
        assertEquals(0, expected.getVolumeM3().compareTo(actual.getVolumeM3()));
        assertEquals(0, expected.getUnitPrice().compareTo(actual.getUnitPrice()));
    }
}
