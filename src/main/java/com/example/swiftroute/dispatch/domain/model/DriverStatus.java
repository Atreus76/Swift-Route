package com.example.swiftroute.dispatch.domain.model;

public enum DriverStatus {
    AVAILABLE,
    ON_ROUTE,
    UNAVAILABLE;

    public boolean canTransitionTo(DriverStatus newStatus) {
        switch (this) {
            case AVAILABLE:
                return newStatus == ON_ROUTE || newStatus == UNAVAILABLE;
            case ON_ROUTE:
                return newStatus == AVAILABLE || newStatus == UNAVAILABLE;
            case UNAVAILABLE:
                return newStatus == AVAILABLE; // Unavailable can only transition back to Available
            default:
                return false;
        }
    }
}
