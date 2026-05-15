package com.example.swiftroute.dispatch.domain.model;

public enum RouteStopStatus {
    PENDING,
    COMPLETED,
    FAILED;

    public boolean canTransitionTo(RouteStopStatus newStatus){
        switch (this){
            case PENDING:
                return newStatus == COMPLETED || newStatus == FAILED;
            case COMPLETED:
                return false; // Completed is a terminal state
            case FAILED:
                return false; // Failed is a terminal state
            default:
                return false;
        }
    }
}
