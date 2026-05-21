package com.example.swiftroute.order.domain.model;

import java.time.Instant;
import java.util.UUID;

public record OrderPlacedEvent(UUID orderId, UUID customerId, Instant occurredAt) {

}
