package com.example.swiftroute.tracking.infrastructure.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.swiftroute.tracking.application.service.TrackingService;
import com.example.swiftroute.tracking.domain.model.TrackingEvent;
import com.example.swiftroute.tracking.infrastructure.web.dto.TrackingEventResponse;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/tracking")
public class TrackingController {
    private final TrackingService trackingService;

    public TrackingController(TrackingService trackingService){
        this.trackingService = trackingService;
    }

    @GetMapping("orders/{orderId}")
    public ResponseEntity<List<TrackingEventResponse>> getTrackingEventsByOrderId(@PathVariable UUID orderId) {
    List<TrackingEvent> trackingEvents = trackingService.getTrackingEventByOrderId(orderId);

    List<TrackingEventResponse> response = trackingEvents.stream()
            .map(TrackingEventResponse::from)
            .toList();

    return ResponseEntity.ok(response);
}

    @PostMapping
    public String addTrackingEvent(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    
    
}
