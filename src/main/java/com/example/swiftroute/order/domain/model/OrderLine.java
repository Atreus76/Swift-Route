package com.example.swiftroute.order.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderLine {
    private  UUID id;
    private  UUID orderId;
    private  String sku;
    private  String description;
    private  int quantity;
    private  BigDecimal weightKg;
    private  BigDecimal volumeM3;
    private  BigDecimal unitPrice;

    protected OrderLine() {}

    private OrderLine(UUID id, UUID orderId, String sku, String description, int quantity, BigDecimal weightKg, BigDecimal volumeM3, BigDecimal unitPrice) {
        this.id = id;
        this.orderId = orderId;
        this.sku = sku;
        this.description = description;
        this.quantity = quantity;
        this.weightKg = weightKg;
        this.volumeM3 = volumeM3;
        this.unitPrice = unitPrice;
    }

    public static OrderLine of(UUID orderId, String sku, String description, int quantity, BigDecimal weightKg, BigDecimal volumeM3, BigDecimal unitPrice) {
        if (orderId == null) {
            throw new IllegalArgumentException("Order ID cannot be null");
        }
        if (sku == null || sku.isEmpty()) {
            throw new IllegalArgumentException("SKU cannot be null or empty");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        if (weightKg == null || weightKg.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Weight must be greater than zero");
        }
        if (volumeM3 == null || volumeM3.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Volume must be greater than zero");
        }
        if (unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Unit price cannot be negative");
        }

        return new OrderLine(UUID.randomUUID(), orderId, sku, description, quantity, weightKg, volumeM3, unitPrice);
    }

    public static OrderLine reconstitute(UUID id, UUID orderId, String sku,
                                      String description, int quantity,
                                      BigDecimal weightKg, BigDecimal volumeM3,
                                      BigDecimal unitPrice) {
    OrderLine line = new OrderLine();
    line.id = id;
    line.orderId = orderId;
    line.sku = sku;
    line.description = description;
    line.quantity = quantity;
    line.weightKg = weightKg;
    line.volumeM3 = volumeM3;
    line.unitPrice = unitPrice;
    return line;
}

    public BigDecimal totalWeight(){
        return weightKg.multiply(BigDecimal.valueOf(quantity));
    }

    public BigDecimal totalVolume(){
        return volumeM3.multiply(BigDecimal.valueOf(quantity));
    }

    public BigDecimal lineTotal(){
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public UUID getId() {
        return id;
    }
    
    public UUID getOrderId() {
        return orderId;
    }
    
    public String getSku() {
        return sku;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getWeightKg() {
        return weightKg;
    }

    public BigDecimal getVolumeM3() {
        return volumeM3;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
}
