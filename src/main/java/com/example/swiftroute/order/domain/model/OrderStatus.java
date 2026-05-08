package com.example.swiftroute.order.domain.model;

public enum OrderStatus {
    PENDING {
        public boolean canTransitionTo(OrderStatus next) {
            return next == CONFIRMED || next == CANCELLED;
        }
    },
    CONFIRMED {
        public boolean canTransitionTo(OrderStatus next) {
            return next == DISPATCHED || next == CANCELLED;
        }
    },
    DISPATCHED {
        public boolean canTransitionTo(OrderStatus next) {
            return next == DELIVERED || next == FAILED;
        }
    },
    DELIVERED,
    CANCELLED,
    FAILED{
        public boolean canTransitionTo(OrderStatus next) {
            return next == CANCELLED; // No transitions allowed from FAILED
        }
    }
}
