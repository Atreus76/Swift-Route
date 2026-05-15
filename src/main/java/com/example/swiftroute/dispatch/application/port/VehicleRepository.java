package com.example.swiftroute.dispatch.application.port;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.swiftroute.dispatch.domain.model.Vehicle;

public interface VehicleRepository {
    void save(Vehicle vehicle);
    Optional<Vehicle> findById(UUID id);
    List<Vehicle> findAll();
}
