package com.example.demo.service.points;
import com.example.demo.model.Receipt;

public class ItemsCountPointsDecorator implements PointsCalculator {
    private final PointsCalculator calculator;

    public ItemsCountPointsDecorator(PointsCalculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public int calculatePoints(Receipt receipt) {
        int points = calculator.calculatePoints(receipt);
        points += (receipt.getItems().size() / 2) * 5;
        return points;
    }
}

