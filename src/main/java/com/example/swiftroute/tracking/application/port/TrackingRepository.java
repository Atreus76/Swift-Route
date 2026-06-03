package com.example.swiftroute.tracking.application.port;

import java.util.List;
import java.util.UUID;

import com.example.swiftroute.tracking.domain.model.TrackingEvent;

public interface TrackingRepository {
    void save(TrackingEvent event);
    List<TrackingEvent> findByOrderId(UUID id);
    List<TrackingEvent> findAll();
}
