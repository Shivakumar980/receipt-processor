package com.example.demo.service;

import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Receipt;
import com.example.demo.repository.ReceiptRepository;
import com.example.demo.service.points.BasePointsCalculator;
import com.example.demo.service.points.DescriptionLengthPointsDecorator;
import com.example.demo.service.points.ItemsCountPointsDecorator;
import com.example.demo.service.points.MultipleOfQuarterPointsDecorator;
import com.example.demo.service.points.OddDayPointsDecorator;
import com.example.demo.service.points.PointsCalculator;
import com.example.demo.service.points.RetailerNamePointsDecorator;
import com.example.demo.service.points.RoundDollarPointsDecorator;
import com.example.demo.service.points.TimePointsDecorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReceiptService {
    private static final Logger logger = LoggerFactory.getLogger(ReceiptService.class);

    private final ReceiptRepository receiptRepository;

    public ReceiptService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    public UUID processReceipt(Receipt receipt) { 
        
        logger.info("Processing receipt for retailer: {}", receipt.getRetailer());
        
        if (receipt.getRetailer().trim().isEmpty()) {
            logger.warn("Retailer name is empty. Throwing BusinessException.");
            throw new BusinessException("Retailer name cannot be empty.");
        }
        if (receipt.getItems().isEmpty()) {
            logger.warn("Receipt has no items. Throwing BusinessException.");
            throw new BusinessException("Receipt must contain at least one item.");
        }

        UUID receiptId = receiptRepository.save(receipt);
        logger.info("Receipt saved successfully with ID: {}", receiptId);
        int points = calculatePoints(receipt);
        logger.info("Calculated {} points for receipt ID: {}", points, receiptId);


        try {
            receiptRepository.savePoints(receiptId, points);
            logger.info("Points saved successfully for receipt ID: {}", receiptId);
        } catch (Exception e) {
            logger.error("Failed to save points for receipt ID: {}", receiptId, e);
            throw new BusinessException("Failed to save points for receipt ID: " + receiptId, e);
        }  

        return receiptId;
    }

   
    public Optional<Integer> getPoints(UUID receiptId) {
        logger.info("Fetching points for receipt ID: {}", receiptId);

        return receiptRepository.getPoints(receiptId)
                .or(() -> {
                    logger.warn("No points found for receipt ID: {}. Throwing ResourceNotFoundException.", receiptId);
                    throw new ResourceNotFoundException("No points found for receipt ID: " + receiptId);
                });
    }


    private int calculatePoints(Receipt receipt) {
        logger.debug("Starting points calculation for retailer: {}", receipt.getRetailer());
        PointsCalculator calculator = new BasePointsCalculator();
        calculator = new RetailerNamePointsDecorator(calculator);
        calculator = new RoundDollarPointsDecorator(calculator);
        calculator = new MultipleOfQuarterPointsDecorator(calculator);
        calculator = new ItemsCountPointsDecorator(calculator);
        calculator = new DescriptionLengthPointsDecorator(calculator);
        calculator = new OddDayPointsDecorator(calculator);
        calculator = new TimePointsDecorator(calculator);

        int points = calculator.calculatePoints(receipt);
        logger.debug("Total points calculated for receipt: {} -> {}", receipt.getRetailer(), points);

        return points;
    }
}
