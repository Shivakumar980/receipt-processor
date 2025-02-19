package com.example.demo.service.points;
import com.example.demo.model.Receipt;

public class TimePointsDecorator implements PointsCalculator {
    private final PointsCalculator calculator;

    public TimePointsDecorator(PointsCalculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public int calculatePoints(Receipt receipt) {
        int points = calculator.calculatePoints(receipt);
        String[] timeParts = receipt.getPurchaseTime().split(":");
        int hour = Integer.parseInt(timeParts[0]);

        if (hour >= 14 && hour < 16) {
            points += 10;
        }

        return points;
    }
}

