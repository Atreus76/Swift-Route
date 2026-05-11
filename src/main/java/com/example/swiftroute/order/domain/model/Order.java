package com.example.swiftroute.order.domain.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.example.swiftroute.order.domain.valueObject.DeliveryAddress;

public class Order {
    private UUID id;
    private UUID customerId;
    private OrderStatus status;
    private DeliveryAddress deliveryAddress;
    private Instant createdAt;
    private Instant updatedAt;
    private Long version;
    private List<OrderLine> orderLines = new ArrayList<>();

    protected Order() {
        // For ORM
    }

    private Order(UUID id, UUID customerId, OrderStatus status, DeliveryAddress deliveryAddress, Instant createdAt, Instant updatedAt, Long version, List<OrderLine> orderLines) {
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
        
        return new Order(UUID.randomUUID(), customerId, OrderStatus.PENDING, deliveryAddress, Instant.now(), Instant.now(), 0L, new ArrayList<>());
    }

    public void confirm(){
        if(!status.canTransitionTo(OrderStatus.CONFIRMED)) {
            throw new IllegalStateException("Cannot transition from " + status + " to CONFIRMED");
        }
        this.status = OrderStatus.CONFIRMED;
        this.updatedAt = Instant.now();
        // this.version++;
    }

    public void cancel(){
        if(!status.canTransitionTo(OrderStatus.CANCELLED)) {
            throw new IllegalStateException("Cannot transition from " + status + " to CANCELLED");
        }
        this.status = OrderStatus.CANCELLED;
        this.updatedAt = Instant.now();
        this.version++;
    }

    public void markDispatched(){
        if(!status.canTransitionTo(OrderStatus.DISPATCHED)) {
            throw new IllegalStateException("Cannot transition from " + status + " to DISPATCHED");
        }
        this.status = OrderStatus.DISPATCHED;
        this.updatedAt = Instant.now();
        this.version++;
    }

    public void markDelivered(){
        if(!status.canTransitionTo(OrderStatus.DELIVERED)) {
            throw new IllegalStateException("Cannot transition from " + status + " to DELIVERED");
        }
        this.status = OrderStatus.DELIVERED;
        this.updatedAt = Instant.now();
        this.version++;
    }

    public void addLine(OrderLine line){
        if(line == null) {
            throw new IllegalArgumentException("Order line cannot be null");
        }
        if (status != OrderStatus.PENDING) {
            throw new IllegalStateException("Can only add lines to PENDING orders");
        }
        this.orderLines.add(line);
        this.updatedAt = Instant.now();
        this.version++;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Long getVersion() {
        return version;
    }

    public List<OrderLine> getOrderLines() {
        return new ArrayList<>(orderLines);
    }
}
