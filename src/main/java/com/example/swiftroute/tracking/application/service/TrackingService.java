package com.example.swiftroute.tracking.application.service;

import java.util.UUID;

import org.springframework.context.event.EventListener;

import com.example.swiftroute.order.domain.model.OrderDeliveredEvent;
import com.example.swiftroute.shared.EntityNotFoundException;
import com.example.swiftroute.tracking.application.port.TrackingRepository;
import com.example.swiftroute.tracking.domain.model.TrackingEvent;

public class TrackingService {
    
    private final TrackingRepository trackingRepository;

    public TrackingService (TrackingRepository trackingRepository){
        this.trackingRepository = trackingRepository;
    }

    @EventListener
    public void on(OrderDeliveredEvent event){
        TrackingEvent trackingEvent = TrackingEvent.create(UUID.randomUUID(), event.occurredAt(), null, null, event.orderId(), null);
        trackingRepository.save(trackingEvent);
    }

    public TrackingEvent getTrackingEventByOrderId(UUID orderId){
        return trackingRepository.findByOrderId(orderId).orElseThrow(
            () -> EntityNotFoundException.of("Tracking Event", orderId)
        );
    }
    
}
