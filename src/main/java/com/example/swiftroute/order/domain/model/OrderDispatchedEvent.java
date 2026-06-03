package com.example.swiftroute.order.domain.model;

import java.time.Instant;
import java.util.UUID;

public record OrderDispatchedEvent(UUID orderId, UUID routeId, Instant occurredAt) {

}
