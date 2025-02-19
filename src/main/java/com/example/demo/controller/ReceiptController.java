package com.example.demo.controller;

import com.example.demo.model.Receipt;
import com.example.demo.service.ReceiptService;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/receipts")
@Validated
public class ReceiptController {

    private static final Logger logger = LoggerFactory.getLogger(ReceiptController.class);
    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/process")
    public ResponseEntity<String> processReceipt(@Valid @RequestBody Receipt receipt) {
        logger.info("Processing receipt for retailer: {}", receipt.getRetailer());

        UUID receiptId = receiptService.processReceipt(receipt);
        logger.info("Receipt processed successfully with ID: {}", receiptId);

        return ResponseEntity.ok("Receipt saved with ID: " + receiptId);
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<Integer> getReceiptPoints(@PathVariable UUID id) {
        logger.info("Fetching points for receipt ID: {}", id);

         Optional<Integer> points = receiptService.getPoints(id);
        if (points.isPresent()) {
            logger.info("Points retrieved: {} for receipt ID: {}", points.get(), id);
            return ResponseEntity.ok(points.get());
        } else {
            logger.warn("Receipt ID {} not found.", id);
            return ResponseEntity.notFound().build();
        }
    }
}
