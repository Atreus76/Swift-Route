package com.example.swiftroute.order.infrastructure.web;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.swiftroute.order.application.service.OrderApplicationService;
import com.example.swiftroute.order.domain.model.Order;
import com.example.swiftroute.order.domain.model.OrderStatus;
import com.example.swiftroute.order.domain.valueObject.DeliveryAddress;
import com.example.swiftroute.order.infrastructure.web.dto.OrderLineRequest;
import com.example.swiftroute.order.infrastructure.web.dto.OrderResponse;
import com.example.swiftroute.order.infrastructure.web.dto.PlaceOrderRequest;
import com.example.swiftroute.shared.domain.Page;
import com.example.swiftroute.shared.domain.PageRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderApplicationService orderService;

    public OrderController(OrderApplicationService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(@Valid @RequestBody PlaceOrderRequest request) {
        DeliveryAddress deliveryAddress = DeliveryAddress.of(
                request.getStreet(),
                request.getCity(),
                request.getPostalCode(),
                request.getCountry());

        Order order = orderService.placeOrder(request.getCustomerId(), deliveryAddress);
        return ResponseEntity.status(HttpStatus.CREATED).body(OrderResponse.from(order));
    }

    @GetMapping
    public ResponseEntity<Page<OrderResponse>> getOrdersByStatus(
            @RequestParam OrderStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Page<Order> orders = orderService.getOrdersByStatus(
                status, new PageRequest(page, size));
        
        Page<OrderResponse> response = new Page<>(
                orders.getContent().stream().map(OrderResponse::from).toList(),
                orders.getPage(),
                orders.getSize(),
                orders.getTotalElements()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable UUID id) {
        Order order = orderService.getOrder(id);
        return ResponseEntity.ok(OrderResponse.from(order));
    }

    @PostMapping("/{id}/confirm")
    public ResponseEntity<Void> confirmOrder(@PathVariable UUID id) {
        orderService.confirmOrder(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelOrder(@PathVariable UUID id) {
        orderService.cancelOrder(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/lines")
    public ResponseEntity<Void> addLine(@PathVariable UUID id, @Valid @RequestBody OrderLineRequest request) {
        orderService.addLine(id, OrderLineRequest.toOrderLine(request));
        return ResponseEntity.ok().build();
    }
}
