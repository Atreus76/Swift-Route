package com.example.swiftroute.dispatch.domain.model;

import java.time.Instant;
import java.util.UUID;

public record RouteStopCompletedEvent(UUID routeId, UUID orderId, Instant occurredAt) {

}
