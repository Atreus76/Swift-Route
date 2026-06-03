package com.example.swiftroute.tracking.infrastructure.persistence;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface TrackingEventMapper {
    void insert(TrackingEventPersistence trackingEvent);
    List<TrackingEventPersistence> findByOrderId(@Param("orderId") UUID orderId);
    boolean existById(@Param("id") UUID id);
}
