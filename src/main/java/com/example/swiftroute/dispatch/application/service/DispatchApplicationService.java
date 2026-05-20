package com.example.swiftroute.dispatch.application.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.swiftroute.dispatch.application.port.DriverRepository;
import com.example.swiftroute.dispatch.application.port.RouteRepository;
import com.example.swiftroute.dispatch.application.port.VehicleRepository;
import com.example.swiftroute.dispatch.domain.model.Driver;
import com.example.swiftroute.dispatch.domain.model.Route;
import com.example.swiftroute.dispatch.domain.model.RouteStop;
import com.example.swiftroute.dispatch.domain.model.Vehicle;
import com.example.swiftroute.shared.EntityNotFoundException;

@Service
public class DispatchApplicationService {
    private final RouteRepository routeRepository;
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;

    public DispatchApplicationService(RouteRepository routeRepository, DriverRepository driverRepository, VehicleRepository vehicleRepository) {
        this.routeRepository = routeRepository;
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional
    public Route createRoute(Date plannedDate){
        Route route = Route.create(UUID.randomUUID(), UUID.randomUUID(),plannedDate);
        routeRepository.save(route);
        return route;
    }

    @Transactional
    public void assignDriver(UUID routeId, UUID driverId, UUID vehicleId) {
        Route route = routeRepository.findById(routeId).orElseThrow(
            () -> EntityNotFoundException.of("Route", routeId));
        Driver driver = driverRepository.findById(driverId).orElseThrow(
            () -> EntityNotFoundException.of("Driver", driverId));
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(
            () -> EntityNotFoundException.of("Vehicle", vehicleId));
        
        route.assignDriver(driver, vehicle);
        driver.markOnRoute();
        vehicle.markReserved();

        routeRepository.save(route);
        driverRepository.save(driver);
        vehicleRepository.save(vehicle);
    }

    @Transactional
    public void addStop(UUID routeId, UUID orderId, int stopSequence){
        Route route = routeRepository.findById(routeId).orElseThrow(
            () -> EntityNotFoundException.of("Route", routeId));
        RouteStop stop = RouteStop.of(routeId, orderId, stopSequence, null);

        route.addStop(stop);

        routeRepository.save(route);
    }

    @Transactional
    public void dispatchRoute(UUID routeId){
        Route route = routeRepository.findById(routeId).orElseThrow(
            () -> EntityNotFoundException.of("Route", routeId));
        Vehicle vehicle = vehicleRepository.findById(route.getVehicleId()).orElseThrow(
            () -> EntityNotFoundException.of("Vehicle", route.getVehicleId())
        );

        route.dispatch();
        vehicle.markInUse();

        routeRepository.save(route);
        vehicleRepository.save(vehicle);
    }

    @Transactional
    public void completeRoute(UUID routeId){
        Route route = routeRepository.findById(routeId).orElseThrow(
            () -> EntityNotFoundException.of("Route", routeId));
        Vehicle vehicle = vehicleRepository.findById(route.getVehicleId()).orElseThrow(
            () -> EntityNotFoundException.of("Vehicle", route.getVehicleId()));
        Driver driver = driverRepository.findById(route.getDriverId()).orElseThrow(
            () -> EntityNotFoundException.of("Driver", route.getDriverId()));

        route.complete();
        vehicle.markAvailable();
        driver.markAvailable();

        routeRepository.save(route);
        vehicleRepository.save(vehicle);
        driverRepository.save(driver);
    }

    public Route getRoute(UUID routeId){
        return routeRepository.findById(routeId).orElseThrow(
            () -> EntityNotFoundException.of("Route", routeId));
    }

    @Transactional
    public Driver createDriver(String name, String licenseNumber, String phoneNumber){
        Driver driver = Driver.register(name, licenseNumber, phoneNumber);
        driverRepository.save(driver);
        return driver;
    }

    @Transactional
    public Vehicle createVehicle(String type, String licensePlate, BigDecimal maxWeightKg, BigDecimal maxVolumeM3){
        Vehicle vehicle = Vehicle.register(UUID.randomUUID(), type, licensePlate, maxWeightKg, maxVolumeM3);
        vehicleRepository.save(vehicle);
        return vehicle;
    }

    public Driver getDriver(UUID driverId){
        return driverRepository.findById(driverId).orElseThrow(
            () -> EntityNotFoundException.of("Driver", driverId));
    }

    public Vehicle getVehicle(UUID vehicleId){
        return vehicleRepository.findById(vehicleId).orElseThrow(
            () -> EntityNotFoundException.of("Vehicle", vehicleId));
    }

}
