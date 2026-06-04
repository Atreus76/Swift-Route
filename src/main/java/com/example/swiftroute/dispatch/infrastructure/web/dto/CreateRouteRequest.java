package com.example.swiftroute.dispatch.infrastructure.web.dto;

import java.sql.Date;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateRouteRequest {
    @NotNull(message = "Planned date is required")
    private Date plannedDate;
}
