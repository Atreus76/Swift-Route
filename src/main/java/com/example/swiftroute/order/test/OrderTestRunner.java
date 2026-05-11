package com.example.swiftroute.order.test;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.swiftroute.order.application.port.OrderRepository;
import com.example.swiftroute.order.domain.model.Order;
import com.example.swiftroute.order.domain.valueObject.DeliveryAddress;

@Component
public class OrderTestRunner implements CommandLineRunner {
    
    private final OrderRepository orderRepository;

    public OrderTestRunner(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create a new order
        Order order = Order.place(
            UUID.randomUUID(),
            DeliveryAddress.of("123 Main St", "Anytown", "12345", "USA")

        );

        // Save the order
        Order savedOrder = orderRepository.save(order);

        System.out.println("Order Saved: " + savedOrder.getId());

        //Load order back
        Order loadedOrder = orderRepository.findById(savedOrder.getId())
            .orElseThrow(() -> new RuntimeException("Order not found"));

        // Verify that the loaded order matches the saved order
        if (savedOrder.getId().equals(loadedOrder.getId())) {
            System.out.println("Order loaded successfully: " + loadedOrder.getId());
        } else {
            System.out.println("Error loading order");
        }
    }
}
