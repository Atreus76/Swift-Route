package com.example.swiftroute.order.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.example.swiftroute.order.domain.valueObject.DeliveryAddress;

public class Order {
    private final UUID id;
    private final UUID customerId;
    private final OrderStatus status;
    private final DeliveryAddress deliveryAddress;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final Long version;
    private final List<OrderLine> orderLines = new ArrayList<>();

    private Order(UUID id, UUID customerId, OrderStatus status, DeliveryAddress deliveryAddress, LocalDateTime createdAt, LocalDateTime updatedAt, Long version, List<OrderLine> orderLines) {
        this.id = id;
        this.customerId = customerId;
        this.status = status;
        this.deliveryAddress = deliveryAddress;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.version = version;
        this.orderLines.addAll(orderLines);
    }

    public static Order place(UUID customerId, DeliveryAddress deliveryAddress) {
        if (customerId == null) {
            throw new IllegalArgumentException("Customer ID cannot be null");
        }
        if (deliveryAddress == null) {
            throw new IllegalArgumentException("Delivery address cannot be null");
        }
        
        return new Order(UUID.randomUUID(), customerId, OrderStatus.PENDING, deliveryAddress, LocalDateTime.now(), LocalDateTime.now(), 0L, new ArrayList<>());
    }

    public Order confirm(){
        if(!status.canTransitionTo(OrderStatus.CONFIRMED)) {
            throw new IllegalStateException("Cannot transition from " + status + " to CONFIRMED");
        }
        return new Order(id, customerId, OrderStatus.CONFIRMED, deliveryAddress, createdAt, LocalDateTime.now(), version + 1, orderLines);
    }

    public Order cancel(String reason) {
        if(!status.canTransitionTo(OrderStatus.CANCELLED)) {
            throw new IllegalStateException("Cannot transition from " + status + " to CANCELLED");
        }
        // Log cancellation reason if needed
        return new Order(id, customerId, OrderStatus.CANCELLED, deliveryAddress, createdAt, LocalDateTime.now(), version + 1, orderLines);
    }

    public Order markDispatched(){
        if(!status.canTransitionTo(OrderStatus.DISPATCHED)) {
            throw new IllegalStateException("Cannot transition from " + status + " to DISPATCHED");
        }
        return new Order(id, customerId, OrderStatus.DISPATCHED, deliveryAddress, createdAt, LocalDateTime.now(), version + 1, orderLines);
    }

    public Order markDelivered(){
        if(!status.canTransitionTo(OrderStatus.DELIVERED)) {
            throw new IllegalStateException("Cannot transition from " + status + " to DELIVERED");
        }
        return new Order(id, customerId, OrderStatus.DELIVERED, deliveryAddress, createdAt, LocalDateTime.now(), version + 1, orderLines);
    }

    public Order addLine(OrderLine line){
        if(line == null) {
            throw new IllegalArgumentException("Order line cannot be null");
        }
        if (status != OrderStatus.PENDING) {
            throw new IllegalStateException("Can only add lines to PENDING orders");
        }
        List<OrderLine> newLines = new ArrayList<>(orderLines);
        newLines.add(line);
        return new Order(id, customerId, status, deliveryAddress, createdAt, LocalDateTime.now(), version + 1, newLines);
    }
    
    public List<OrderLine> getLines() {
        return Collections.unmodifiableList(orderLines);
    }

    
    
    public UUID getId() {
        return id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Long getVersion() {
        return version;
    }

    public List<OrderLine> getOrderLines() {
        return new ArrayList<>(orderLines);
    }
}
