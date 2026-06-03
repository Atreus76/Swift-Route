package com.example.swiftroute.dispatch.domain.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Route {
    private UUID id;
    private UUID driverId;
    private UUID vehicleId;
    private RouteStatus status;
    private Date plannedDate;
    private List<RouteStop> stops = new ArrayList<>();

    private Route(UUID id, UUID driverId, UUID vehicleId, RouteStatus status, Date plannedDate, List<RouteStop> stops) {
        this.id = id;
        this.driverId = driverId;
        this.vehicleId = vehicleId;
        this.status = status;
        this.plannedDate = plannedDate;
        this.stops = stops != null ? new ArrayList<>(stops) : new ArrayList<>();
    }

    protected Route() {
        // For ORM
    }

    public static Route create(Date plannedDate) {
        if(plannedDate == null) {
            throw new IllegalArgumentException("Planned Date must be provided");
        }
        return new Route(UUID.randomUUID(), null, null, RouteStatus.PENDING, plannedDate, null);
    }

    public static Route reconstitute(UUID id, UUID driverId, UUID vehicleId, RouteStatus status, Date plannedDate, List<RouteStop> stops) {
        return new Route(id, driverId, vehicleId, status, plannedDate, stops);
    }

    public void assignDriver(Driver driver, Vehicle vehicle) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver cannot be null");
        }
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        if (!driver.getStatus().equals(DriverStatus.AVAILABLE)) {
            throw new IllegalStateException("Driver must be available to be assigned to a route");
        }
        if (!vehicle.getStatus().equals(VehicleStatus.AVAILABLE)) {
            throw new IllegalStateException("Vehicle must be available to be assigned to a route");
        }
        this.driverId = driver.getId();
        this.vehicleId = vehicle.getId();
        this.status = RouteStatus.ASSIGNED;

    }

    public void addStop(RouteStop stop) {
        if (stop == null) {
            throw new IllegalArgumentException("Route stop cannot be null");
        }
        if (status != RouteStatus.ASSIGNED) {
            throw new IllegalStateException("Can only add stops to routes that are assigned or in progress");
        }
        this.stops.add(stop);
    }

    public void dispatch(){
        if(vehicleId == null){
            throw new IllegalStateException("Cannot dispatch route without an assigned vehicle");
        }
        if (status != RouteStatus.ASSIGNED) {
            throw new IllegalStateException("Only assigned routes can be dispatched");
        }
        this.status = RouteStatus.IN_PROGRESS;
    }

    public void complete(){
        if (status != RouteStatus.IN_PROGRESS) {
            throw new IllegalStateException("Only routes in progress can be completed");
        }
        this.status = RouteStatus.COMPLETED;
    }

    public void completeStop(UUID stopId){
        RouteStop targetStop = this.stops.stream()
            .filter(stop -> stop.getId().equals(stopId))
            .findFirst()
            .orElseThrow();

        targetStop.completeStop();
    }

    public UUID getId() {
        return id;
    }

    public UUID getDriverId() {
        return driverId;
    }

    public UUID getVehicleId() {
        return vehicleId;
    }

    public RouteStatus getStatus() {
        return status;
    }

    public Date getPlannedDate() {
        return plannedDate;
    }

    public List<RouteStop> getStops() {
        return new ArrayList<>(stops);
    }
    
}
