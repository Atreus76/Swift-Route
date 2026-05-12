package com.example.swiftroute.order.infrastructure.web.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.example.swiftroute.order.domain.model.Order;
import com.example.swiftroute.order.domain.model.OrderLine;
import com.example.swiftroute.order.domain.model.OrderStatus;
import com.example.swiftroute.order.domain.valueObject.DeliveryAddress;

import lombok.Data;

@Data
public class OrderResponse {
    private UUID id;
    private UUID customerId;
    private OrderStatus status;
    private DeliveryAddress deliveryAddress;
    private Instant createdAt;
    private Instant updatedAt;
    private Long version;
    private List<OrderLine> orderLines;

    public static OrderResponse from(Order order){
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setCustomerId(order.getCustomerId());
        response.setStatus(order.getStatus());
        response.setDeliveryAddress(order.getDeliveryAddress());
        response.setCreatedAt(order.getCreatedAt());
        response.setUpdatedAt(order.getUpdatedAt());
        response.setVersion(order.getVersion());
        response.setOrderLines(order.getOrderLines());
        return response;
    }
}
