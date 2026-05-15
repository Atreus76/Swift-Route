package com.example.swiftroute.dispatch.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.swiftroute.dispatch.application.port.VehicleRepository;
import com.example.swiftroute.dispatch.domain.model.Vehicle;

@Repository
public class VehicleRepositoryAdapter implements VehicleRepository {
    private final VehicleMapper vehicleMapper;

    public VehicleRepositoryAdapter(VehicleMapper vehicleMapper) {
        this.vehicleMapper = vehicleMapper;
    }

    @Override
    public void save(Vehicle vehicle) {
        if (vehicleMapper.existById(vehicle.getId())){
            vehicleMapper.update(vehicle);
        } else {
            vehicleMapper.insert(vehicle);
        }
    }

    @Override
    public Optional<Vehicle> findById(UUID id) {
        return vehicleMapper.findById(id);
    }

    @Override
    public List<Vehicle> findAll() {
        return vehicleMapper.findAll();
    }



}
