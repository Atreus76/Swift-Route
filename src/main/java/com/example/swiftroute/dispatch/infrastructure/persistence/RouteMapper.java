package com.example.swiftroute.dispatch.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.swiftroute.dispatch.domain.model.RouteStatus;

@Mapper
public interface RouteMapper {
    void insert(RoutePersistence route);
    void update(RoutePersistence route);
    Optional<RoutePersistence> findById(@Param("id") UUID id);
    RoutePersistence findByStatus(@Param("status") RouteStatus status);
    RoutePersistence findByDriverId(@Param("driverId") UUID driverId);
    void insertStop(RouteStopPersistence stop);
    int deleteStopsByRouteId(@Param("routeId") UUID routeId);
    boolean existById(@Param("id") UUID id);
}
