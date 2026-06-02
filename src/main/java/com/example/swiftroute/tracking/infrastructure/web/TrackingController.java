package com.example.swiftroute.tracking.infrastructure.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.swiftroute.tracking.application.service.TrackingService;
import com.example.swiftroute.tracking.domain.model.TrackingEvent;
import com.example.swiftroute.tracking.infrastructure.web.dto.TrackingEventResponse;

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
    public ResponseEntity<TrackingEventResponse> getTrackingEvent(@PathVariable UUID orderId) {
        TrackingEvent trackingEvent = trackingService.getTrackingEventByOrderId(orderId);
        return ResponseEntity.ok(TrackingEventResponse.from(trackingEvent));
    }

    @PostMapping
    public String addTrackingEvent(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    
    
}
