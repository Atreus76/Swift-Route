package com.example.swiftroute.dispatch.domain.model;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record RouteDispatchedEvent(UUID routeId, List<UUID> orderIds, Instant occurredAt) {

}
