package com.example.swiftroute.order.infrastructure.web.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.example.swiftroute.order.domain.model.OrderLine;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderLineRequest {
    @NotNull(message = "Order ID is required")
    private UUID orderId;
    @NotNull(message = "SKU is required")
    private String sku;
    @NotNull(message = "Description is required")
    private String description;
    @NotNull(message = "Quantity is required")
    private int quantity;
    @NotNull(message = "Weight (kg) is required")
    private BigDecimal weightKg;
    @NotNull(message = "Volume (m3) is required")
    private BigDecimal volumeM3;
    @NotNull(message = "Unit price is required")
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
