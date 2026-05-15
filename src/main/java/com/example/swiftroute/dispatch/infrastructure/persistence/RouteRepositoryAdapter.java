package com.example.swiftroute.dispatch.infrastructure.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.swiftroute.dispatch.application.port.RouteRepository;
import com.example.swiftroute.dispatch.domain.model.Route;
import com.example.swiftroute.dispatch.domain.model.RouteStatus;

@Repository
public class RouteRepositoryAdapter implements RouteRepository {
    private final RouteMapper routeMapper;

    public RouteRepositoryAdapter(RouteMapper routeMapper) {
        this.routeMapper = routeMapper;
    }

    @Override
    public void save(Route route) {
        if (routeMapper.existById(route.getId())) {
            routeMapper.update(route);
        } else {
            routeMapper.insert(route);
        }
    }

    @Override
    public Optional<Route> findById(java.util.UUID id) {
        return routeMapper.findById(id);
    }

    @Override
    public Route findByStatus(RouteStatus status) {
        throw new UnsupportedOperationException("Unimplemented method 'findByStatus'");
    }

    @Override
    public Route findByDriverId(java.util.UUID driverId) {
        throw new UnsupportedOperationException("Unimplemented method 'findByDriverId'");
    }


}
