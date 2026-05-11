package com.example.swiftroute;

import java.math.BigDecimal;
import java.util.UUID;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.swiftroute.order.application.port.OrderRepository;
import com.example.swiftroute.order.domain.model.Order;
import com.example.swiftroute.order.domain.valueObject.DeliveryAddress;


@MapperScan("com.example.swiftroute.order.infrastructure.persistence")
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

	@Bean
CommandLineRunner test(OrderRepository orderRepository) {
    return args -> {
        DeliveryAddress address = DeliveryAddress.of("123 Main St", "Hanoi", "100000", "Vietnam");
        Order order = Order.place(UUID.randomUUID(), address);
        orderRepository.save(order);

        Order loaded = orderRepository.findById(order.getId()).orElseThrow();
        System.out.println("Status: " + loaded.getStatus()); // should print PENDING
    };
}

}
