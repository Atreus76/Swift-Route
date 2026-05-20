package com.example.swiftroute.dispatch.infrastructure.web.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class AssignDriverRequest {
    private UUID driverId;

    private UUID vehicleId;
}
