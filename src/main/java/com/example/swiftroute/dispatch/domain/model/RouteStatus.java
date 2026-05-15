package com.example.swiftroute.dispatch.domain.model;

public enum RouteStatus {
    PENDING,
    ASSIGNED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED;

    public boolean canTransitionTo(RouteStatus newStatus){
        switch (this) {
            case PENDING:
                return newStatus == ASSIGNED || newStatus == CANCELLED;
            case ASSIGNED:
                return newStatus == IN_PROGRESS || newStatus == CANCELLED;
            case IN_PROGRESS:
                return newStatus == COMPLETED || newStatus == CANCELLED;
            case COMPLETED:                
                return false; // Completed is a terminal state
            case CANCELLED:                
                return false; // Cancelled is a terminal state
            default:                
                return false;
        }
    }
}
