package com.example.swiftroute.order.infrastructure;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.example.swiftroute.order.application.port.PricingStrategy;
import com.example.swiftroute.order.domain.model.Order;

public class WeightBasedPricingStrategy implements PricingStrategy{

    private static final BigDecimal BASE_FEE = BigDecimal.valueOf(5.00);
    private static final BigDecimal RATE_PER_KG = BigDecimal.valueOf(0.80);

    @Override
    public BigDecimal calculate(Order order) {
        BigDecimal weightFee = order.totalWeight().multiply(RATE_PER_KG);
        return BASE_FEE.add(weightFee).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String strategyName() {
        return "WeightBasedStrategy";
    }

}
