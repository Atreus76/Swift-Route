package com.example.swiftroute.dispatch.domain.model;

import java.time.Instant;
import java.util.UUID;

public class RouteStop {
    private UUID id;
    private UUID routeId;
    private UUID orderId;
    private int stopSequence;
    private Instant ETA;
    private RouteStopStatus status;

    private RouteStop(UUID id, UUID routeId, UUID orderId, int stopSequence, Instant ETA, RouteStopStatus status) {
        this.id = id;
        this.routeId = routeId;
        this.orderId = orderId;
        this.stopSequence = stopSequence;
        this.ETA = ETA;
        this.status = status;
    }

    public static RouteStop of(UUID routeId, UUID orderId, int stopSequence, Instant ETA) {
        if (routeId == null) {
            throw new IllegalArgumentException("Route ID cannot be null");
        }
        if (orderId == null) {
            throw new IllegalArgumentException("Order ID cannot be null");
        }
        if (stopSequence < 0) {
            throw new IllegalArgumentException("Stop sequence must be non-negative");
        }
        if (ETA == null || ETA.isBefore(Instant.now())) {
            throw new IllegalArgumentException("ETA must be a future timestamp");
        }

        return new RouteStop(UUID.randomUUID(), routeId, orderId, stopSequence, ETA, RouteStopStatus.PENDING);
    }

    public void completeStop(){
        if (!status.equals(RouteStopStatus.PENDING)) {
            throw new IllegalStateException("Only routes in progress can be completed");
        }
        this.status = RouteStopStatus.COMPLETED;
    }

    public UUID getId() {
        return id;
    }

    public UUID getRouteId() {
        return routeId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public int getStopSequence() {
        return stopSequence;
    }

    public Instant getETA() {
        return ETA;
    }

    public RouteStopStatus getStatus() {
        return status;
    }
}
