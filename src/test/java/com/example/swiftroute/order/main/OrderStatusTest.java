package com.example.swiftroute.order.main;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.example.swiftroute.order.domain.model.OrderStatus;

public class OrderStatusTest {
    // 1. PENDING can transition to CONFIRMED
    @Test
    void pending_ShouldBeAbleToTransitionToConfirmed() {
        // Arrange
        OrderStatus currentStatus = OrderStatus.PENDING;

        // Act
        boolean result = currentStatus.canTransitionTo(OrderStatus.CONFIRMED);

        // Assert
        assertTrue(result, "PENDING should be allowed to transition to CONFIRMED");
    }

    // 2. PENDING can transition to CANCELLED
    @Test
    void pending_ShouldBeAbleToTransitionToCancelled() {
        // Arrange
        OrderStatus currentStatus = OrderStatus.PENDING;

        // Act
        boolean result = currentStatus.canTransitionTo(OrderStatus.CANCELLED);

        // Assert
        assertTrue(result, "PENDING should be allowed to transition to CANCELLED");
    }

    // 3. PENDING cannot transition to DELIVERED
    @Test
    void pending_ShouldNotBeAbleToTransitionToDelivered() {
        // Arrange
        OrderStatus currentStatus = OrderStatus.PENDING;

        // Act
        boolean result = currentStatus.canTransitionTo(OrderStatus.DELIVERED);

        // Assert
        assertFalse(result, "PENDING should NOT be allowed to transition directly to DELIVERED");
    }

    // 4. DELIVERED cannot transition to anything
    @Test
    void delivered_ShouldNotBeAbleToTransitionToAnyStatus() {
        // Arrange
        OrderStatus currentStatus = OrderStatus.DELIVERED;

        // Assert against all possible statuses loop to guarantee it's a true terminal state
        for (OrderStatus targetStatus : OrderStatus.values()) {
            boolean result = currentStatus.canTransitionTo(targetStatus);
            assertFalse(result, "DELIVERED is a terminal state and should not transition to " + targetStatus);
        }
    }

    // 5. CONFIRMED can transition to DISPATCHED
    @Test
    void confirmed_ShouldBeAbleToTransitionToDispatched() {
        // Arrange
        OrderStatus currentStatus = OrderStatus.CONFIRMED;

        // Act
        boolean result = currentStatus.canTransitionTo(OrderStatus.DISPATCHED);

        // Assert
        assertTrue(result, "CONFIRMED should be allowed to transition to DISPATCHED");
    }
}
