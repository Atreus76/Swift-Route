package com.example.swiftroute.order.infrastructure.web.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlaceOrderRequest {
    @NotNull
    private String street;

    @NotNull
    private String city;

    @NotNull
    private String postalCode;

    @NotNull
    private String country;
}
