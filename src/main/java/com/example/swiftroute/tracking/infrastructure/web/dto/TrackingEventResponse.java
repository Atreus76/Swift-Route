package com.example.swiftroute.tracking.infrastructure.web.dto;

import java.time.Instant;
import java.util.UUID;

import com.example.swiftroute.tracking.domain.model.TrackingEvent;
import com.example.swiftroute.tracking.domain.model.TrackingEventType;

import lombok.Data;

@Data
public class TrackingEventResponse {
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

    public static TrackingEventResponse from(TrackingEvent trackingEvent){
        TrackingEventResponse response = new TrackingEventResponse();
        response.setId(trackingEvent.getId());
        response.setTimestamp(trackingEvent.getTimestamp());
        response.setEventType(trackingEvent.getEventType());
        response.setDescription(trackingEvent.getDescription());
        response.setOrderId(trackingEvent.getOrderId());
        response.setRouteId(trackingEvent.getRouteId());
        response.setRouteStopId(trackingEvent.getRouteStopId());
        response.setLatitude(trackingEvent.getLatitude());
        response.setLongtitude(trackingEvent.getLongtitude());
        return response;
    }
}
