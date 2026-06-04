package com.example.swiftroute.order.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.example.swiftroute.order.domain.valueObject.DeliveryAddress;

public class DeliveryAddressTest {
// 1. of() creates address with correct fields
    @Test
    void of_shouldCreateAddressWithCorrectFields() {
        // Act
        DeliveryAddress address = DeliveryAddress.of("123 Main St", "Gotham", "12345", "USA");

        // Assert
        assertNotNull(address);
        // Since fields are private and don't have getters shown, we verify construction 
        // by comparing it to another instance with identical fields using your equals() method.
        assertEquals(DeliveryAddress.of("123 Main St", "Gotham", "12345", "USA"), address);
    }

    // 2. of() throws when street is blank
    @Test
    void of_shouldThrowWhenStreetIsBlank() {
        // Act + Assert (Testing empty string "")
        IllegalArgumentException exceptionEmpty = assertThrows(
            IllegalArgumentException.class,
            () -> DeliveryAddress.of("", "Gotham", "12345", "USA")
        );
        assertEquals("Street cannot be null or empty", exceptionEmpty.getMessage());

        // Act + Assert (Testing null)
        IllegalArgumentException exceptionNull = assertThrows(
            IllegalArgumentException.class,
            () -> DeliveryAddress.of(null, "Gotham", "12345", "USA")
        );
        assertEquals("Street cannot be null or empty", exceptionNull.getMessage());
    }

    // 3. of() throws when city is null
    @Test
    void of_shouldThrowWhenCityIsNull() {
        // Act + Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> DeliveryAddress.of("123 Main St", null, "12345", "USA")
        );
        assertEquals("City cannot be null or empty", exception.getMessage());
    }

    // 4. Two addresses with same fields are equal
    @Test
    void shouldBeEqualWhenFieldsAreSame() {
        // Arrange
        DeliveryAddress address1 = DeliveryAddress.of("123 Main St", "Gotham", "12345", "USA");
        DeliveryAddress address2 = DeliveryAddress.of("123 Main St", "Gotham", "12345", "USA");

        // Assert
        assertTrue(address1.equals(address2));
        assertTrue(address2.equals(address1)); // Symmetry check
        assertEquals(address1.hashCode(), address2.hashCode(), "HashCodes must match if objects are equal");
    }

    // 5. Two addresses with different fields are not equal
    @Test
    void shouldNotBeEqualWhenFieldsAreDifferent() {
        // Arrange
        DeliveryAddress baseAddress = DeliveryAddress.of("123 Main St", "Gotham", "12345", "USA");
        DeliveryAddress differentStreet = DeliveryAddress.of("456 Oak Rd", "Gotham", "12345", "USA");
        DeliveryAddress differentCity = DeliveryAddress.of("123 Main St", "Metropolis", "12345", "USA");

        // Assert
        assertNotEquals(baseAddress, differentStreet);
        assertNotEquals(baseAddress, differentCity);
        assertNotEquals(baseAddress, null); // Check against null
        assertNotEquals(baseAddress, "Just a random String object"); // Check against different type
    }
}
