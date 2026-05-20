package com.example.swiftroute.dispatch.infrastructure.web.dto;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import com.example.swiftroute.dispatch.domain.model.Route;
import com.example.swiftroute.dispatch.domain.model.RouteStatus;
import com.example.swiftroute.dispatch.domain.model.RouteStop;

import lombok.Data;

@Data
public class RouteResponse {
    private UUID id;
    private UUID driverId;
    private UUID vehicleId;
    private RouteStatus status;
    private Date plannedDate;
    private List<RouteStop> stops;

    public static RouteResponse from(Route route){
        RouteResponse response = new RouteResponse();
        response.setId(route.getId());
        response.setDriverId(route.getDriverId());
        response.setVehicleId(route.getVehicleId());
        response.setStatus(route.getStatus());
        response.setPlannedDate(route.getPlannedDate());
        response.setStops(route.getStops());
        return response;
    }
}
