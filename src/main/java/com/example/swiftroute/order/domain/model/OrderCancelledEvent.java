package com.example.swiftroute.order.domain.model;

import java.time.Instant;
import java.util.UUID;

public record OrderCancelledEvent(UUID orderId, String reason, Instant occurredAt) {

}
