package com.example.swiftroute.tracking.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.example.swiftroute.order.domain.model.OrderDeliveredEvent;
import com.example.swiftroute.order.domain.model.OrderDispatchedEvent;
import com.example.swiftroute.tracking.application.port.TrackingRepository;
import com.example.swiftroute.tracking.domain.model.TrackingEvent;
import com.example.swiftroute.tracking.domain.model.TrackingEventType;
@Service
public class TrackingService {
    
    private final TrackingRepository trackingRepository;

    public TrackingService (TrackingRepository trackingRepository){
        this.trackingRepository = trackingRepository;
    }

    @EventListener
    public void on(OrderDispatchedEvent event){
        TrackingEvent trackingEvent = TrackingEvent.create(TrackingEventType.OUT_FOR_DELIVERY, "Order dispatched", event.orderId(), event.routeId());
        System.out.println(">>> TrackingService received OrderDispatchedEvent for order: " + event.orderId());
        trackingRepository.save(trackingEvent);
    }

    @EventListener
    public void on(OrderDeliveredEvent event){
        TrackingEvent trackingEvent = TrackingEvent.create(TrackingEventType.DELIVERED, "Order delivered", event.orderId(), event.routeId());
        System.out.println(">>> TrackingService received OrderDeliveredEvent for order: " + event.orderId());
        trackingRepository.save(trackingEvent);
    }

    public List<TrackingEvent> getTrackingEventByOrderId(UUID orderId){
        return trackingRepository.findByOrderId(orderId);
    }

    public void addTrackingEvent(TrackingEvent event){
        trackingRepository.save(event);
    }



    
    
}
