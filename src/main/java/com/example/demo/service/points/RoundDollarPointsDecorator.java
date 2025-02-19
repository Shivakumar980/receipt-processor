package com.example.demo.service.points;
import com.example.demo.model.Receipt;

import java.math.BigDecimal;

public class RoundDollarPointsDecorator implements PointsCalculator {
    private final PointsCalculator calculator;

    public RoundDollarPointsDecorator(PointsCalculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public int calculatePoints(Receipt receipt) {
        int points = calculator.calculatePoints(receipt);
        BigDecimal totalAmount = new BigDecimal(receipt.getTotal());

        if (totalAmount.stripTrailingZeros().scale() <= 0) {
            points += 50;
        }

        return points;
    }
}

