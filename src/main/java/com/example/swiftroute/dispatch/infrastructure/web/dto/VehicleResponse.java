package com.example.swiftroute.dispatch.infrastructure.web.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.example.swiftroute.dispatch.domain.model.Vehicle;
import com.example.swiftroute.dispatch.domain.model.VehicleStatus;

import lombok.Data;

@Data
public class VehicleResponse {
    private UUID id;
    private String type;
    private String licensePlate;
    private VehicleStatus status;
    private BigDecimal maxWeightKg;
    private BigDecimal maxVolumeM3;

    public static VehicleResponse from(Vehicle vehicle){
        VehicleResponse response = new VehicleResponse();
        response.setId(vehicle.getId());
        response.setType(vehicle.getType());
        response.setLicensePlate(vehicle.getLicensePlate());
        response.setStatus(vehicle.getStatus());
        response.setMaxWeightKg(vehicle.getMaxWeightKg());
        response.setMaxVolumeM3(vehicle.getMaxVolumeM3());
        return response;
    }
}
