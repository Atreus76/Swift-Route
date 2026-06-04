package com.example.swiftroute.dispatch.infrastructure.web.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateVehicleRequest {
    @NotBlank(message = "Vehicle type is required")
    private String type;
    @NotBlank(message = "License plate is required")
    private String licensePlate;
    @NotNull(message = "Max weight is required")
    private BigDecimal maxWeightKg;
    @NotNull(message = "Max volume is required")
    private BigDecimal maxVolumeM3;
}
