package com.example.swiftroute;

import java.math.BigDecimal;
import java.util.UUID;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.swiftroute.order.application.service.OrderApplicationService;
import com.example.swiftroute.order.domain.model.Order;
import com.example.swiftroute.order.domain.valueObject.DeliveryAddress;


@MapperScan({
    "com.example.swiftroute.order.infrastructure.persistence",
    "com.example.swiftroute.dispatch.infrastructure.persistence",
	"com.example.swiftroute.tracking.infrastructure.persistence"
})
@SpringBootApplication
public class SwiftrouteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwiftrouteApplication.class, args);
		// Order order = Order.place(java.util.UUID.randomUUID(), com.example.swiftroute.order.domain.valueObject.DeliveryAddress.of("123 Main St", "Anytown", "12345", "USA"));
		
		// System.out.println("Order status: " + order.getStatus());
		// order.confirm();
		// System.out.println("After confirm: " + order.getStatus());
		// order.confirm();
		// System.out.println("After second confirm: " + order.getStatus());
		
		
		
	}

// 	@Bean
// CommandLineRunner test(OrderApplicationService orderService) {
//     return args -> {
//         // 1. Create and save
//         DeliveryAddress address = DeliveryAddress.of("123 Main St", "Hanoi", "100000", "Vietnam");
//         Order order = orderService.placeOrder(UUID.randomUUID(), address);
//         System.out.println("Saved order: " + order.getId());

//         // 2. Load back
//         Order loaded = orderService.getOrder(order.getId());
//         System.out.println("Loaded status: " + loaded.getStatus()); // PENDING

//         // 3. Confirm and save again
//         orderService.confirmOrder(order.getId());

//         // 4. Load again and verify
//         Order confirmed = orderService.getOrder(order.getId());
//         System.out.println("After confirm: " + confirmed.getStatus()); // CONFIRMED
//     };
// }

}
