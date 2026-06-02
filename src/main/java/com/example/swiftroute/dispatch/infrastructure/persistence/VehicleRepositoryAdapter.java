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
    private final VehicleConverter vehicleConverter;

    public VehicleRepositoryAdapter(VehicleMapper vehicleMapper, VehicleConverter vehicleConverter) {
        this.vehicleMapper = vehicleMapper;
        this.vehicleConverter = vehicleConverter;
    }

    @Override
    public void save(Vehicle vehicle) {
        VehiclePersistence vehiclePersistence = vehicleConverter.toPersistence(vehicle);
        if (vehicleMapper.existById(vehicle.getId())){
            vehicleMapper.update(vehiclePersistence);
        } else {
            vehicleMapper.insert(vehiclePersistence);
        }
    }

    @Override
    public Optional<Vehicle> findById(UUID id) {
        return vehicleMapper.findById(id).map(vehicleConverter::toDomain);
    }

    @Override
    public List<Vehicle> findAll() {
        return vehicleMapper.findAll().stream().map(vehicleConverter::toDomain).collect(java.util.stream.Collectors.toList());
    }



}
