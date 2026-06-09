package com.example.swiftroute.order.infrastructure;

import java.math.BigDecimal;
import java.util.Set;

import com.example.swiftroute.order.application.port.PricingStrategy;
import com.example.swiftroute.order.domain.model.Order;

public class ZoneBasedPricingStrategy implements PricingStrategy{

   private static final BigDecimal VIETNAM_BASE_FEE = BigDecimal.valueOf(3.00);
    private static final BigDecimal SOUTHEAST_ASIA_BASE_FEE = BigDecimal.valueOf(8.00);
    private static final BigDecimal OTHER_BASE_FEE = BigDecimal.valueOf(15.00);

    private static final BigDecimal RATE_PER_KG = BigDecimal.valueOf(0.50);

    private static final Set<String> SOUTHEAST_ASIA_COUNTRIES = Set.of(
            "thailand",
            "singapore",
            "malaysia",
            "cambodia",
            "laos"
    );

    @Override
    public BigDecimal calculate(Order order) {
        BigDecimal totalWeight = order.totalWeight();
        String destinationCountry = order.getDeliveryAddress().getCountry();

        BigDecimal baseFee = getBaseFee(destinationCountry);

        return baseFee.add(totalWeight.multiply(RATE_PER_KG));
    }

    private BigDecimal getBaseFee(String destinationCountry) {
        String country = destinationCountry.trim().toLowerCase();

        if (country.equals("vietnam")) {
            return VIETNAM_BASE_FEE;
        }

        if (SOUTHEAST_ASIA_COUNTRIES.contains(country)) {
            return SOUTHEAST_ASIA_BASE_FEE;
        }

        return OTHER_BASE_FEE;
    }

    @Override
    public String strategyName() {
        return "Zone Based Pricing";
    }
    
}
