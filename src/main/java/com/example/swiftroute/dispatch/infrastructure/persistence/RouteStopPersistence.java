package com.example.swiftroute.dispatch.infrastructure.persistence;

import java.time.Instant;
import java.util.UUID;


public class RouteStopPersistence {
    private UUID id;
    private UUID routeId;
    private UUID orderId;
    private int stopSequence;
    private Instant ETA;
    private String status;

    public RouteStopPersistence() {
        // For ORM
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getRouteId() {
        return routeId;
    }

    public void setRouteId(UUID routeId) {
        this.routeId = routeId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public int getStopSequence() {
        return stopSequence;
    }

    public void setStopSequence(int stopSequence) {
        this.stopSequence = stopSequence;
    }

    public Instant getETA() {
        return ETA;
    }

    public void setETA(Instant eTA) {
        ETA = eTA;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
