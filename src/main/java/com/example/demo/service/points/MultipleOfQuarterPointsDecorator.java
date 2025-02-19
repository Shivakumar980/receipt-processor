package com.example.demo.service.points;
import com.example.demo.model.Receipt;

import java.math.BigDecimal;

public class MultipleOfQuarterPointsDecorator implements PointsCalculator {
    private final PointsCalculator calculator;

    public MultipleOfQuarterPointsDecorator(PointsCalculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public int calculatePoints(Receipt receipt) {
        int points = calculator.calculatePoints(receipt);
        BigDecimal totalAmount = new BigDecimal(receipt.getTotal());

        if (totalAmount.remainder(new BigDecimal("0.25")).compareTo(BigDecimal.ZERO) == 0) {
            points += 25;
        }

        return points;
    }
}
