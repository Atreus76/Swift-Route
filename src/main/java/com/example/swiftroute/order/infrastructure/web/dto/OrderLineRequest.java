package com.example.swiftroute.order.infrastructure.web.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.example.swiftroute.order.domain.model.OrderLine;

import lombok.Data;

@Data
public class OrderLineRequest {
    private UUID orderId;
    private String sku;
    private String description;
    private int quantity;
    private BigDecimal weightKg;
    private BigDecimal volumeM3;
    private BigDecimal unitPrice;

    public static OrderLine toOrderLine(OrderLineRequest request){
        return OrderLine.of(
            request.getOrderId(), 
            request.getSku(), 
            request.getDescription(), 
            request.getQuantity(), 
            request.getWeightKg(), 
            request.getVolumeM3(), 
            request.getUnitPrice());
    }
}
