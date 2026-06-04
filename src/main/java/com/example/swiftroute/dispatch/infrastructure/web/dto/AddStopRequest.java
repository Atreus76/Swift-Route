package com.example.swiftroute.dispatch.infrastructure.web.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddStopRequest {
    @NotNull(message = "Order ID is required")
    private UUID orderId;
    @NotNull(message = "Stop sequence is required")
    private int stopSequence;
}
