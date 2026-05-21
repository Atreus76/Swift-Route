package com.example.swiftroute.order.domain.model;

import java.time.Instant;
import java.util.UUID;

public record OrderConfirmedEvent(UUID orderId, Instant occurredAt) {

}
