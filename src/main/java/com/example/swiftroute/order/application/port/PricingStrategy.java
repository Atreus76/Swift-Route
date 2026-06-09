package com.example.swiftroute.order.application.port;

import java.math.BigDecimal;

import com.example.swiftroute.order.domain.model.Order;

public interface PricingStrategy {
    BigDecimal calculate(Order order);
    String strategyName();
}
