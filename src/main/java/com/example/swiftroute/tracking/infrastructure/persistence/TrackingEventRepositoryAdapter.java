package com.example.swiftroute.tracking.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.swiftroute.tracking.application.port.TrackingRepository;
import com.example.swiftroute.tracking.domain.model.TrackingEvent;
@Repository
public class TrackingEventRepositoryAdapter implements TrackingRepository{

    private final TrackingEventMapper trackingEventMapper;

    public TrackingEventRepositoryAdapter(TrackingEventMapper trackingEventMapper){
        this.trackingEventMapper = trackingEventMapper;
    }
    
    
    @Override
    public void save(TrackingEvent event) {
        if(trackingEventMapper.existById(event.getId())){
            trackingEventMapper.update(event);
        }else {
            trackingEventMapper.insert(event);
        }
    }

    @Override
    public Optional<TrackingEvent> findById(UUID id) {
        return trackingEventMapper.findById(id);    
    }

    @Override
    public List<TrackingEvent> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }


    @Override
    public Optional<TrackingEvent> findByOrderId(UUID id) {
        return trackingEventMapper.findByOrderId(id);
    }
    
}
