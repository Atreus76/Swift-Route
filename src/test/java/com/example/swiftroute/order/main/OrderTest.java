package com.example.swiftroute.order.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.swiftroute.order.domain.model.Order;
import com.example.swiftroute.order.domain.model.OrderLine;
import com.example.swiftroute.order.domain.model.OrderStatus;
import com.example.swiftroute.order.domain.valueObject.DeliveryAddress;

public class OrderTest {
    private DeliveryAddress dummyAddress;
    private UUID dummyCustomerId;
    private OrderLine dummyLine;

    @BeforeEach
    void setUp() {
        dummyCustomerId = UUID.randomUUID();
        dummyAddress = DeliveryAddress.of("123 Main St", "Hanoi", "100000", "Vietnam");
    }

    // 1. place() creates order with PENDING status
    @Test
    void place_shouldCreateOrderWithPendingStatus(){
        Order order = Order.place(dummyCustomerId, dummyAddress);
        assertEquals(OrderStatus.PENDING, order.getStatus());
    }

    // 2. place() throws when customerId is null
    @Test
    void place_shouldThrowWhenCustomerIdIsNull(){
        //Arrange
        UUID invalidCustomerId = null;
        
        //Act + Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () ->  Order.place(invalidCustomerId, dummyAddress));

        assertEquals("Customer ID cannot be null", exception.getMessage());

    }

    // 3. confirm() transitions PENDING to CONFIRMED
    @Test
    void confirm_shouldTransitionToConfirmed(){
        Order order = Order.place(dummyCustomerId, dummyAddress);

        order.confirm();

        assertEquals(OrderStatus.CONFIRMED, order.getStatus());
    }

    // 4. confirm() throws when order is not PENDING
    @Test
    void confirm_shouldThrowWhenNotPending(){
        OrderStatus invalidOrderStatus = OrderStatus.CANCELLED;

        Order order = Order.reconstitute(dummyCustomerId, dummyCustomerId, 
            invalidOrderStatus, dummyAddress, null, null, 
            null, null);
            
        IllegalStateException exception = assertThrows(
            IllegalStateException.class,
            () -> order.confirm());

        assertTrue(exception.getMessage().contains("Cannot transition from " + invalidOrderStatus + " to CONFIRMED"));
    }

    // 5. cancel() transitions PENDING to CANCELLED
    @Test
    void cancel_shouldTransitionToCancelled(){
        Order order = Order.place(dummyCustomerId, dummyAddress);

        order.cancel();

        assertEquals(OrderStatus.CANCELLED, order.getStatus());
    }

    // 6. cancel() throws when order is DELIVERED
    @Test
    void cancel_shouldThrowWhenDelivered(){
        OrderStatus invalidOrderStatus = OrderStatus.DELIVERED;

        Order order = Order.reconstitute(dummyCustomerId, dummyCustomerId, 
            invalidOrderStatus, dummyAddress, null, null, 
            null, null);
            
        IllegalStateException exception = assertThrows(
            IllegalStateException.class,
            () -> order.cancel());

        assertTrue(exception.getMessage().contains("Cannot transition from " + invalidOrderStatus + " to CANCELLED"));
    }

    // 7. addLine() throws when order is not PENDING
    @Test
    void addLine_shouldThrowWhenNotPending(){
        OrderStatus invalidOrderStatus = OrderStatus.CANCELLED;
        dummyLine = OrderLine.of(
            dummyCustomerId, 
            "Test data", 
            "Test data",
            12, 
            BigDecimal.valueOf(12), 
            BigDecimal.valueOf(12), 
            BigDecimal.valueOf(12));

        Order order = Order.reconstitute(dummyCustomerId, dummyCustomerId, 
            invalidOrderStatus, dummyAddress, null, null, 
            null, null);
            
        IllegalStateException exception = assertThrows(
            IllegalStateException.class,
            () -> order.addLine(dummyLine));

        assertTrue(exception.getMessage().contains("Can only add lines to PENDING orders"));
    }
}
