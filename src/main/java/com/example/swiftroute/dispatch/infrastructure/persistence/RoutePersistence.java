package com.example.swiftroute.dispatch.infrastructure.persistence;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class RoutePersistence {
    private UUID id;
    private UUID driverId;
    private UUID vehicleId;
    private String status;
    private Date plannedDate;
    private List<RouteStopPersistence> stops = new ArrayList<>();

    public RoutePersistence() {
        // For ORM
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getDriverId() {
        return driverId;
    }

    public void setDriverId(UUID driverId) {
        this.driverId = driverId;
    }

    public UUID getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(UUID vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(Date plannedDate) {
        this.plannedDate = plannedDate;
    }

    public List<RouteStopPersistence> getStops() {
        return stops;
    }

    public void setStops(List<RouteStopPersistence> stops) {
        this.stops = stops;
    }

    
}
