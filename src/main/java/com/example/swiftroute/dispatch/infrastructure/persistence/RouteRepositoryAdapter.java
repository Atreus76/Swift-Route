package com.example.swiftroute.dispatch.infrastructure.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.swiftroute.dispatch.application.port.RouteRepository;
import com.example.swiftroute.dispatch.domain.model.Route;
import com.example.swiftroute.dispatch.domain.model.RouteStatus;

@Repository
public class RouteRepositoryAdapter implements RouteRepository {
    private final RouteMapper routeMapper;
    private final RouteConverter routeConverter;

    public RouteRepositoryAdapter(RouteMapper routeMapper, RouteConverter routeConverter) {
        this.routeMapper = routeMapper;
        this.routeConverter = routeConverter;
    }

    @Override
    public void save(Route route) {
        RoutePersistence persistence = routeConverter.toPersistence(route);
        if (routeMapper.existById(persistence.getId())) {
            routeMapper.update(persistence);
        } else {
            routeMapper.insert(persistence);
        }
    }

    @Override
    public Optional<Route> findById(java.util.UUID id) {
        return routeMapper.findById(id)
                .map(routeConverter::toDomain);
    }

    @Override
    public Route findByStatus(RouteStatus status) {
        return routeConverter.toDomain(routeMapper.findByStatus(status));
    }

    @Override
    public Route findByDriverId(java.util.UUID driverId) {
        return routeConverter.toDomain(routeMapper.findByDriverId(driverId));
    }


}
