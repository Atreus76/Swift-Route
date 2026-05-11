package com.example.swiftroute;

import java.math.BigDecimal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.swiftroute.order.domain.model.Order;

@SpringBootApplication
public class SwiftrouteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwiftrouteApplication.class, args);
		Order order = Order.place(java.util.UUID.randomUUID(), com.example.swiftroute.order.domain.valueObject.DeliveryAddress.of("123 Main St", "Anytown", "12345", "USA"));
		
		// order.addLine(com.example.swiftroute.order.domain.model.OrderLine.of(
		// 	java.util.UUID.randomUUID(),
		// 	"test-sku",
		// 	"test description",
		// 	12,
		// 	new BigDecimal("0.45"),
        //     new BigDecimal("0.003"),
        //     new BigDecimal("1499.99")));
		
		
		
	}

}
