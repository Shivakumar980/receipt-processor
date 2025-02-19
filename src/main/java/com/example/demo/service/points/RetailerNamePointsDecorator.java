package com.example.demo.service.points;
import com.example.demo.model.Receipt;

public class RetailerNamePointsDecorator implements PointsCalculator {
    private final PointsCalculator calculator;

    public RetailerNamePointsDecorator(PointsCalculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public int calculatePoints(Receipt receipt) {
        int points = calculator.calculatePoints(receipt);
        points += receipt.getRetailer().replaceAll("[^a-zA-Z0-9]", "").length();
        return points;
    }
}

