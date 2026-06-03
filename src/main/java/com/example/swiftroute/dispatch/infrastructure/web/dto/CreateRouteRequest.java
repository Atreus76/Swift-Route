package com.example.swiftroute.dispatch.infrastructure.web.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class CreateRouteRequest {
    private Date plannedDate;
}
