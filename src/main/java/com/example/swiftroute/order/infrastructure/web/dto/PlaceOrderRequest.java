package com.example.swiftroute.order.infrastructure.web.dto;


import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlaceOrderRequest {
    @NotNull(message = "Customer ID is required")
    private UUID customerId;

    @NotNull(message = "Street is required")
    private String street;

    @NotNull(message = "City is required")
    private String city;

    @NotNull(message = "Postal code is required")
    private String postalCode;

    @NotNull(message = "Country is required")
    private String country;
}
