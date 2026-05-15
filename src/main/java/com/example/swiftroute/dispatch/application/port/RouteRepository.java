package com.example.swiftroute.dispatch.application.port;

import java.util.Optional;
import java.util.UUID;

import com.example.swiftroute.dispatch.domain.model.Route;
import com.example.swiftroute.dispatch.domain.model.RouteStatus;

public interface RouteRepository {
    void save(Route route);
    Optional<Route> findById(UUID id);
    Route findByStatus(RouteStatus status);
    Route findByDriverId(UUID driverId);
}
