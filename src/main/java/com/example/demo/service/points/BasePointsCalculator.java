package com.example.demo.service.points;
import com.example.demo.model.Receipt;

public class BasePointsCalculator implements PointsCalculator {
    @Override
    public int calculatePoints(Receipt receipt) {
        return 0;  
    }
}
