package com.example.swiftroute.tracking.infrastructure.persistence;

import java.time.Instant;
import java.util.UUID;


public class TrackingEventPersistence {
    private UUID id; 
    // Links to your domains 
    private UUID orderId; 
    private UUID routeId; 
    private UUID routeStopId;


    private String status; 
    private String description; 
    private Instant timestamp; 
    private Double latitude;
    private Double longtitude;

    public TrackingEventPersistence() {
        // For ORM
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getRouteId() {
        return routeId;
    }

    public void setRouteId(UUID routeId) {
        this.routeId = routeId;
    }

    public UUID getRouteStopId() {
        return routeStopId;
    }

    public void setRouteStopId(UUID routeStopId) {
        this.routeStopId = routeStopId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    
}
