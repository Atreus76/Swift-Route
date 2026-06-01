package com.example.swiftroute.dispatch.infrastructure.web.dto;

import java.util.UUID;

import com.example.swiftroute.dispatch.domain.model.Driver;
import com.example.swiftroute.dispatch.domain.model.DriverStatus;

import lombok.Data;
@Data
public class DriverResponse {
    private UUID id;
    private String name;
    private String licenseNumber;
    private String phoneNumber;
    private DriverStatus status;

    public static DriverResponse from(Driver driver){
        DriverResponse response = new DriverResponse();
        response.setId(driver.getId());
        response.setName(driver.getName());
        response.setLicenseNumber(driver.getLicenseNumber());
        response.setPhoneNumber(driver.getPhoneNumber());
        response.setStatus(driver.getStatus());
        return response;
    }
}
