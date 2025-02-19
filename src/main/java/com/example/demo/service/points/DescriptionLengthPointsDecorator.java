package com.example.demo.service.points;
import com.example.demo.model.Receipt;
import com.example.demo.model.Item;

import java.math.BigDecimal;

public class DescriptionLengthPointsDecorator implements PointsCalculator {
    private final PointsCalculator calculator;

    public DescriptionLengthPointsDecorator(PointsCalculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public int calculatePoints(Receipt receipt) {
        int points = calculator.calculatePoints(receipt);

        for (Item item : receipt.getItems()) {
            int descLength = item.getShortDescription().trim().length();
            if (descLength % 3 == 0) {
                BigDecimal itemPrice = new BigDecimal(item.getPrice());
                points += (int) Math.ceil(itemPrice.multiply(new BigDecimal("0.2")).doubleValue());
            }
        }

        return points;
    }
}
