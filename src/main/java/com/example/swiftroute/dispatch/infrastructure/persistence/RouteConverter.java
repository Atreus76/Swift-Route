package com.example.swiftroute.dispatch.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.swiftroute.dispatch.domain.model.Route;
import com.example.swiftroute.dispatch.domain.model.RouteStatus;
import com.example.swiftroute.dispatch.domain.model.RouteStop;
import com.example.swiftroute.dispatch.domain.model.RouteStopStatus;
@Component
public class RouteConverter {
    public RoutePersistence toPersistence(Route route) {
        if (route == null){
            return null;
        }

        RoutePersistence persistence = new RoutePersistence();
        persistence.setId(route.getId());
        persistence.setDriverId(route.getDriverId());
        persistence.setVehicleId(route.getVehicleId());
        persistence.setStatus(route.getStatus().name());
        persistence.setPlannedDate(route.getPlannedDate());
        persistence.setStops(
            route.getStops() != null
                    ? route.getStops().stream()
                        .map(this::toStopPersistence)
                        .collect(Collectors.toList())
                    : new ArrayList<>()
        );
        return persistence;
    }

    public Route toDomain(RoutePersistence persistence) {
        if(persistence == null){
            return null;
        }

        List<RouteStop> stops = persistence.getStops() != null
            ? persistence.getStops().stream()
                .map(stopPersistence -> toStopDomain(stopPersistence))
                .collect(Collectors.toList())
            : new ArrayList<>();

        return Route.reconstitute(
            persistence.getId(),
            persistence.getDriverId(),
            persistence.getVehicleId(),
            RouteStatus.valueOf(persistence.getStatus()),
            persistence.getPlannedDate(),
            stops
        );
    }

    private RouteStopPersistence toStopPersistence(RouteStop stop){
        if (stop == null){
            return null;
        }

        RouteStopPersistence persistence = new RouteStopPersistence();
        persistence.setId(stop.getId());
        persistence.setRouteId(stop.getRouteId());
        persistence.setOrderId(stop.getOrderId());
        persistence.setStopSequence(stop.getStopSequence());
        persistence.setETA(stop.getETA());
        persistence.setStatus(stop.getStatus().name());
        return persistence;
    }

    private RouteStop toStopDomain(RouteStopPersistence persistence){
        if (persistence == null){
            return null;
        }

        return RouteStop.reconstitute(
            persistence.getId(),
            persistence.getRouteId(),
            persistence.getOrderId(),
            persistence.getStopSequence(),
            persistence.getETA(),
            RouteStopStatus.valueOf(persistence.getStatus())
        );
    }
}
