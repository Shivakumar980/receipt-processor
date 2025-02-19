package com.example.demo.repository;

import com.example.demo.model.Receipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ReceiptRepository {

    private static final Logger logger = LoggerFactory.getLogger(ReceiptRepository.class);

    private final Map<UUID, Receipt> receiptStorage = new HashMap<>();
    private final Map<UUID, Integer> pointsStorage = new HashMap<>();

    public UUID save(Receipt receipt) {
        UUID receiptId = receipt.getId();
        receiptStorage.put(receiptId, receipt);
        logger.info("Receipt saved with ID: {}", receiptId);
        return receiptId;
    }

    public void savePoints(UUID receiptId, int points) {
        pointsStorage.put(receiptId, points);
        logger.info("Points {} saved for receipt ID: {}", points, receiptId);
    }

    public Optional<Integer> getPoints(UUID receiptId) {
        if (pointsStorage.containsKey(receiptId)) {
            logger.info("Points retrieved for receipt ID: {}", receiptId);
        } else {
            logger.warn("No points found for receipt ID: {}", receiptId);
        }
        return Optional.ofNullable(pointsStorage.get(receiptId));
    }
}
