package com.example.swiftroute.dispatch.infrastructure.web.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AssignDriverRequest {
    @NotNull(message = "Driver ID is required")
    private UUID driverId;
    @NotNull(message = "Vehicle ID is required")
    private UUID vehicleId;
}
