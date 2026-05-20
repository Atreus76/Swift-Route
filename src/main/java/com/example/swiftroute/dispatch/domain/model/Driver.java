package com.example.swiftroute.dispatch.domain.model;

import java.util.UUID;

public class Driver {
    private UUID id;
    private String name;
    private String licenseNumber;
    private String phoneNumber;
    private DriverStatus status;


    protected Driver() {
        // For ORM
    }

    private Driver(UUID id, String name, String licenseNumber, String phoneNumber, DriverStatus status) {
        this.id = id;
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public static Driver register(String name, String licenseNumber, String phoneNumber) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (licenseNumber == null || licenseNumber.isEmpty()) {
            throw new IllegalArgumentException("License number cannot be null or empty");
        }
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }

        return new Driver(UUID.randomUUID(), name, licenseNumber, phoneNumber, DriverStatus.AVAILABLE);
    }

    public void markAvailable(){
        if (status == DriverStatus.AVAILABLE){
            throw new IllegalStateException("Driver is already available");
        }
        this.status = DriverStatus.AVAILABLE;
    }

    public void markOnRoute() {
        if (status == DriverStatus.ON_ROUTE) {
            throw new IllegalStateException("Driver is already assigned to a route");
        }
        this.status = DriverStatus.ON_ROUTE;
    }

    public void markUnavailable() {
        if (status == DriverStatus.UNAVAILABLE) {
            throw new IllegalStateException("Driver is already unavailable");
        }
        this.status = DriverStatus.UNAVAILABLE;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public DriverStatus getStatus() {
        return status;
    }
}
