package com.example.swiftroute.dispatch.infrastructure.persistence;

import org.springframework.stereotype.Component;

import com.example.swiftroute.dispatch.domain.model.Vehicle;
import com.example.swiftroute.dispatch.domain.model.VehicleStatus;
@Component
public class VehicleConverter {
    public VehiclePersistence toPersistence(Vehicle vehicle) {
        if (vehicle == null) {
            return null;
        }

        VehiclePersistence persistence = new VehiclePersistence();
        persistence.setId(vehicle.getId());
        persistence.setType(vehicle.getType());
        persistence.setLicensePlate(vehicle.getLicensePlate());
        persistence.setStatus(vehicle.getStatus().name());
        persistence.setMaxWeightKg(vehicle.getMaxWeightKg());
        persistence.setMaxVolumeM3(vehicle.getMaxVolumeM3());
        return persistence;
    }
    public Vehicle toDomain(VehiclePersistence vehiclePersistence) {
        if (vehiclePersistence == null) {
            return null;
        }
        return Vehicle.reconstitute(
            vehiclePersistence.getId(),
            vehiclePersistence.getType(),
            vehiclePersistence.getLicensePlate(),
            VehicleStatus.valueOf(vehiclePersistence.getStatus()),
            vehiclePersistence.getMaxWeightKg(),
            vehiclePersistence.getMaxVolumeM3()
        );
    }
}
