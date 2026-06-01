package com.example.swiftroute.dispatch.infrastructure.web.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateDriverRequest {
    @NotNull
    private String name;
    @NotNull
    private String licenseNumber;
    @NotNull
    private String phoneNumber;
}
