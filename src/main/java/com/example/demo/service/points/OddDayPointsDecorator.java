package com.example.demo.service.points;
import com.example.demo.model.Receipt;

public class OddDayPointsDecorator implements PointsCalculator {
    private final PointsCalculator calculator;

    public OddDayPointsDecorator(PointsCalculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public int calculatePoints(Receipt receipt) {
        int points = calculator.calculatePoints(receipt);
        int purchaseDay = Integer.parseInt(receipt.getPurchaseDate().split("-")[2]);

        if (purchaseDay % 2 != 0) {
            points += 6;
        }

        return points;
    }
}
