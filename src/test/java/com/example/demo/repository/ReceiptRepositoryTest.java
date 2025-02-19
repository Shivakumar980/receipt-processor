package com.example.demo.repository;

import com.example.demo.model.Item;
import com.example.demo.model.Receipt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ReceiptRepositoryTest {

    @InjectMocks
    private ReceiptRepository receiptRepository;

    private Receipt receipt;
    private UUID receiptId;

    @BeforeEach
    public void setUp() {
       
        Item item = new Item("Laptop", "999.99");
        List<Item> items = new ArrayList<>();
        items.add(item);
        receipt = new Receipt("Walmart", "2025-02-15", "12:30", items, "999.99");
        receiptId = receipt.getId();
    }

    @Test
    public void testSaveReceipt() {
       
        UUID savedReceiptId = receiptRepository.save(receipt);

       
        assertEquals(receiptId, savedReceiptId);

    }

    @Test
    public void testSavePoints() {
       
        int points = 100;
        receiptRepository.savePoints(receiptId, points);

        
        Optional<Integer> retrievedPoints = receiptRepository.getPoints(receiptId);

        
        assertTrue(retrievedPoints.isPresent());
        assertEquals(points, retrievedPoints.get());
    }

    @Test
    public void testGetPointsNoPointsSaved() {
        
        Optional<Integer> retrievedPoints = receiptRepository.getPoints(receiptId);

        
        assertFalse(retrievedPoints.isPresent());
    }

    @Test
    public void testGetPoints() {
        int points = 150;
        receiptRepository.savePoints(receiptId, points);

        
        Optional<Integer> retrievedPoints = receiptRepository.getPoints(receiptId);

        
        assertTrue(retrievedPoints.isPresent());
        assertEquals(points, retrievedPoints.get());
    }
}
