package com.example.swiftroute.order.infrastructure.persistence;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.swiftroute.order.domain.model.Order;
import com.example.swiftroute.order.domain.model.OrderLine;
import com.example.swiftroute.order.domain.model.OrderStatus;
import com.example.swiftroute.order.domain.valueObject.DeliveryAddress;

public class OrderConverter {
    public Order toDomain(OrderPersistence persistence) { 
        if (persistence == null){
            return null;
        }

        DeliveryAddress address = DeliveryAddress.of(
        persistence.getDeliveryAddress().getStreet(),
        persistence.getDeliveryAddress().getCity(),
        persistence.getDeliveryAddress().getPostalCode(),
        persistence.getDeliveryAddress().getCountry()
    );

    List<OrderLine> lines = persistence.getOrderLines() != null
        ? persistence.getOrderLines().stream()
            .map(linePersistence -> toLineDomain(linePersistence))
            .collect(Collectors.toList())
        : new ArrayList<>();

    return Order.reconstitute(
        persistence.getId(),
        persistence.getCustomerId(),
        OrderStatus.valueOf(persistence.getStatus()),
        address,
        persistence.getCreatedAt(),
        persistence.getUpdatedAt(),
        persistence.getVersion(),
        lines
    );
    }
    public OrderPersistence toPersistence(Order order) { 
        if (order == null){
            return null;
        }

        OrderPersistence persistence = new OrderPersistence();
        persistence.setId(order.getId());
        persistence.setCustomerId(order.getCustomerId());
        persistence.setStatus(order.getStatus().name());
        persistence.setDeliveryAddress(order.getDeliveryAddress());
        persistence.setCreatedAt(order.getCreatedAt());
        persistence.setUpdatedAt(order.getUpdatedAt());
        persistence.setVersion(order.getVersion());
        persistence.setOrderLines(
            order.getOrderLines() != null
                    ? order.getOrderLines().stream()
                        .map(this::toLinePersistence)
                        .collect(Collectors.toList())
                    : new ArrayList<>()
);
        return persistence;
    }

    private OrderLine toLineDomain(OrderLinePersistence linePersistence) {
    if (linePersistence == null) {
        return null;
    }

    return OrderLine.reconstitute(
            linePersistence.getId(),
            linePersistence.getOrderId(),
            linePersistence.getSku(),
            linePersistence.getDescription(),
            linePersistence.getQuantity(),
            linePersistence.getWeightKg(),
            linePersistence.getVolumeM3(),
            linePersistence.getUnitPrice()
    );
}

    private OrderLinePersistence toLinePersistence(OrderLine line) {
    if (line == null) {
        return null;
    }

    OrderLinePersistence persistence = new OrderLinePersistence();

    persistence.setId(line.getId());
    persistence.setOrderId(line.getOrderId());
    persistence.setSku(line.getSku());
    persistence.setDescription(line.getDescription());
    persistence.setQuantity(line.getQuantity());
    persistence.setWeightKg(line.getWeightKg());
    persistence.setVolumeM3(line.getVolumeM3());
    persistence.setUnitPrice(line.getUnitPrice());

    return persistence;
}
}
