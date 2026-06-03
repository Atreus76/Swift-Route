package com.example.swiftroute.tracking.infrastructure.persistence;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.swiftroute.tracking.application.port.TrackingRepository;
import com.example.swiftroute.tracking.domain.model.TrackingEvent;
@Repository
public class TrackingEventRepositoryAdapter implements TrackingRepository{

    private final TrackingEventMapper trackingEventMapper;
    private final TrackingEventConverter trackingEventConverter;

    public TrackingEventRepositoryAdapter(TrackingEventMapper trackingEventMapper, TrackingEventConverter trackingEventConverter){
        this.trackingEventMapper = trackingEventMapper;
        this.trackingEventConverter = trackingEventConverter;
    }
    
    
    @Override
    public void save(TrackingEvent event) {
        TrackingEventPersistence persistence = trackingEventConverter.toPersistence(event);
        trackingEventMapper.insert(persistence);
    }


    @Override
    public List<TrackingEvent> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }


    @Override
    public List<TrackingEvent> findByOrderId(UUID orderId) {
    return trackingEventMapper.findByOrderId(orderId)
            .stream()
            .map(trackingEventConverter::toDomain)
            .toList();
    }
    
}
