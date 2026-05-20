package com.example.swiftroute.dispatch.infrastructure.web.dto;

import java.sql.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class CreateRouteRequest {
    private UUID driverId;

    private UUID vehicleId;

    private Date plannedDate;
}
