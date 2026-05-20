package com.example.swiftroute.dispatch.infrastructure.web.dto;

import com.example.swiftroute.dispatch.domain.model.DriverStatus;

import lombok.Data;

@Data
public class CreateDriverRequest {
    private String name;
    private String licenseNumber;
    private String phoneNumber;
    private DriverStatus status;
}
