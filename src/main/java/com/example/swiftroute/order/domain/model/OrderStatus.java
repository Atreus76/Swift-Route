package com.example.swiftroute.order.domain.model;

public enum OrderStatus {
    PENDING,
    CONFIRMED,
    DISPATCHED,
    DELIVERED,
    CANCELLED,
    FAILED;

    public boolean canTransitionTo(OrderStatus newStatus) {
        switch (this) {
            case PENDING:
                return newStatus == CONFIRMED || newStatus == CANCELLED;
            case CONFIRMED:
                return newStatus == DISPATCHED || newStatus == CANCELLED;
            case DISPATCHED:
                return newStatus == DELIVERED || newStatus == FAILED;
            case DELIVERED:
                return false; // Delivered is a terminal state
            case CANCELLED:
                return false; // Cancelled is a terminal state
            case FAILED:
                return newStatus == CANCELLED; // Failed is a terminal state
            default:
                return false;
        }
    }
}
