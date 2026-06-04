package com.example.swiftroute.dispatch.infrastructure.web.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateDriverRequest {
    @NotNull(message = "Driver name is required")
    private String name;
    @NotNull(message = "License number is required")
    private String licenseNumber;
    @NotNull(message = "Phone number is required")
    private String phoneNumber;
}
