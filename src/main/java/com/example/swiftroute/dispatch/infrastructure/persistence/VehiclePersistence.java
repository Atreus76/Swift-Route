package com.example.swiftroute.dispatch.infrastructure.persistence;

import java.math.BigDecimal;
import java.util.UUID;


public class VehiclePersistence {
    private UUID id;
    private String type;
    private String licensePlate;
    private String status;
    private BigDecimal maxWeightKg;
    private BigDecimal maxVolumeM3;
    
    public VehiclePersistence() {
        // For ORM
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getMaxWeightKg() {
        return maxWeightKg;
    }

    public void setMaxWeightKg(BigDecimal maxWeightKg) {
        this.maxWeightKg = maxWeightKg;
    }

    public BigDecimal getMaxVolumeM3() {
        return maxVolumeM3;
    }

    public void setMaxVolumeM3(BigDecimal maxVolumeM3) {
        this.maxVolumeM3 = maxVolumeM3;
    }

    
}
