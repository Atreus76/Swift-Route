package com.example.swiftroute.dispatch.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Vehicle {
    private UUID id;
    private String type;
    private String licensePlate;
    private VehicleStatus status;
    private BigDecimal maxWeightKg;
    private BigDecimal maxVolumeM3;

    protected Vehicle() {
        // For ORM
    }

    private Vehicle(UUID id, String type, String licensePlate, VehicleStatus status, BigDecimal maxWeightKg, BigDecimal maxVolumeM3) {
        this.id = id;
        this.type = type;
        this.licensePlate = licensePlate;
        this.status = status;
        this.maxWeightKg = maxWeightKg;
        this.maxVolumeM3 = maxVolumeM3;
    }

    public static Vehicle register(UUID id, String type, String licensePlate, BigDecimal maxWeightKg, BigDecimal maxVolumeM3) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Type cannot be null or empty");
        }
        if (licensePlate == null || licensePlate.isEmpty()) {
            throw new IllegalArgumentException("License plate cannot be null or empty");
        }
        if (maxWeightKg == null || maxWeightKg.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Max weight must be greater than zero");
        }
        if (maxVolumeM3 == null || maxVolumeM3.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Max volume must be greater than zero");
        }

        return new Vehicle(id, type, licensePlate, VehicleStatus.AVAILABLE, maxWeightKg, maxVolumeM3);
    }

    public void markUnavailable() {
        if (status == VehicleStatus.UNAVAILABLE) {
            throw new IllegalStateException("Vehicle is already unavailable");
        }
        this.status = VehicleStatus.UNAVAILABLE;
    }

    public void markInUse() {
        if (status == VehicleStatus.IN_USE) {
            throw new IllegalStateException("Vehicle is already in use");
        }
        this.status = VehicleStatus.IN_USE;
    }

    public UUID getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }
    
    public VehicleStatus getStatus() {
        return status;
    }

    public BigDecimal getMaxWeightKg() {
        return maxWeightKg;
    }

    public BigDecimal getMaxVolumeM3() {
        return maxVolumeM3;
    }


}
