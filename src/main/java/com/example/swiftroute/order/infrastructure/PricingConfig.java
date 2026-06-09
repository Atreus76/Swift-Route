package com.example.swiftroute.order.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.swiftroute.order.application.port.PricingStrategy;

@Configuration
public class PricingConfig {

    @Value("${swiftroute.pricing.strategy}")
    private String strategy;

    @Bean
    public PricingStrategy pricingStrategy(
            WeightBasedPricingStrategy weightBased,
            ZoneBasedPricingStrategy zoneBased) {
        return switch (strategy) {
            case "zone-based" -> zoneBased;
            default -> weightBased;
        };
    }
}
