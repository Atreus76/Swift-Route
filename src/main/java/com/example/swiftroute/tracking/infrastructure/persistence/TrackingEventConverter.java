package com.example.swiftroute.tracking.infrastructure.persistence;

import org.springframework.stereotype.Component;

import com.example.swiftroute.tracking.domain.model.TrackingEvent;
import com.example.swiftroute.tracking.domain.model.TrackingEventType;
@Component
public class TrackingEventConverter {
    public TrackingEventPersistence toPersistence(TrackingEvent event){
        if (event == null) {
            return null;
        }

        TrackingEventPersistence persistence = new TrackingEventPersistence();
        persistence.setId(event.getId());
        persistence.setOrderId(event.getOrderId());
        persistence.setRouteId(event.getRouteId());
        persistence.setRouteStopId(event.getRouteStopId());
        persistence.setStatus(event.getStatus().name());
        persistence.setDescription(event.getDescription());
        persistence.setTimestamp(event.getTimestamp());
        persistence.setLatitude(event.getLatitude());
        persistence.setLongtitude(event.getLongtitude());
        return persistence;
    }

    public TrackingEvent toDomain(TrackingEventPersistence persistence){
        if (persistence == null) {
            return null;
        }

        
        return TrackingEvent.reconstitute(
            persistence.getId(),
            persistence.getTimestamp(),
            TrackingEventType.valueOf(persistence.getStatus()),
            persistence.getDescription(),
            persistence.getOrderId(),
            persistence.getRouteId(),
            persistence.getRouteStopId(),
            persistence.getLatitude(),
            persistence.getLongtitude()
        );
    }
}
