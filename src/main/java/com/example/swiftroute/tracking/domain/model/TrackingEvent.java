package com.example.swiftroute.tracking.domain.model;

import java.time.Instant;
import java.util.UUID;

public class TrackingEvent {
    private UUID id; 
    private Instant timestamp; 
    private TrackingEventType eventType; 
    private String description; 

    // Links to your domains 
    private UUID orderId; 
    private UUID routeId; 
    private UUID routeStopId;

    private Double latitude;
    private Double longtitude;

    protected TrackingEvent(){

    }

    private TrackingEvent(UUID id, Instant timestamp, TrackingEventType eventType, String description, UUID orderId,
            UUID routeId, UUID routeStopId, Double latitude, Double longtitude) {
        this.id = id;
        this.timestamp = timestamp;
        this.eventType = eventType;
        this.description = description;
        this.orderId = orderId;
        this.routeId = routeId;
        this.routeStopId = routeStopId;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public static TrackingEvent create(UUID id, Instant timestamp, TrackingEventType eventType, String description, UUID orderId,
            UUID routeId){
                if(id == null || orderId == null || routeId == null){
                    throw new IllegalArgumentException("Event ID, Order ID and Route ID must be provided");
                }
                return new TrackingEvent(UUID.randomUUID(), timestamp, eventType, description, orderId, routeId, UUID.randomUUID(), null, null);
            }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public Instant getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
    public TrackingEventType getEventType() {
        return eventType;
    }
    public void setEventType(TrackingEventType eventType) {
        this.eventType = eventType;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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
