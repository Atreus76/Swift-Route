package com.example.swiftroute.order.infrastructure.persistence;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.swiftroute.order.domain.model.OrderLine;
import com.example.swiftroute.order.domain.valueObject.DeliveryAddress;

public class OrderPersistence {
    private UUID id;
    private UUID customerId;
    private String status;
    private DeliveryAddress deliveryAddress;
    private Instant createdAt;
    private Instant updatedAt;
    private Long version;
    private List<OrderLinePersistence> orderLines = new ArrayList<>();

    public OrderPersistence() {
        // For ORM
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<OrderLinePersistence> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLinePersistence> orderLines) {
        this.orderLines = orderLines;
    }

    

}
