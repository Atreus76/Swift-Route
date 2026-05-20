package com.example.swiftroute.dispatch.infrastructure.web.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class AddStopRequest {
    private UUID orderId;

    private int stopSequence;
}
