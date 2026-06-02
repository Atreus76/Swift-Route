package com.example.swiftroute.tracking.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.swiftroute.tracking.domain.model.TrackingEvent;

@Mapper
public interface TrackingEventMapper {
    void insert(TrackingEvent trackingEvent);
    int update(TrackingEvent trackingEvent);
    Optional<TrackingEvent> findById(@Param("id") UUID id);
    Optional<TrackingEvent> findByOrderId(@Param("orderId") UUID orderId);
    boolean existById(@Param("id") UUID id);
}
