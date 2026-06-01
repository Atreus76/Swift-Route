package com.example.swiftroute.dispatch.infrastructure.web.dto;

import java.math.BigDecimal;

import com.example.swiftroute.dispatch.domain.model.VehicleStatus;

import lombok.Data;

@Data
public class CreateVehicleRequest {
    private String type;
    private String licensePlate;
    private BigDecimal maxWeightKg;
    private BigDecimal maxVolumeM3;
}
