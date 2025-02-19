package com.example.demo.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Item;
import com.example.demo.model.Receipt;
import com.example.demo.repository.ReceiptRepository;

import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ReceiptServiceTest {

    @Mock
    private ReceiptRepository receiptRepository;

    @InjectMocks
    private ReceiptService receiptService;

    @Test
    void shouldProcessReceiptSuccessfully() {
        Receipt receipt = new Receipt("Walmart", "2024-02-13", "14:30",
                                      List.of(new Item("Soap", "2.99")), "10.00");

        when(receiptRepository.save(receipt)).thenReturn(receipt.getId());

        UUID receiptId = receiptService.processReceipt(receipt);

        assertNotNull(receiptId);
        verify(receiptRepository, times(1)).save(receipt);
        verify(receiptRepository, times(1)).savePoints(eq(receiptId), anyInt());
    }

    @Test
    void shouldThrowExceptionWhenReceiptHasNoItems() {
        Receipt receipt = new Receipt("Walmart", "2024-02-13", "14:30",
                                      Collections.emptyList(), "10.00");

        Exception exception = assertThrows(BusinessException.class, () -> {
            receiptService.processReceipt(receipt);
        });

        assertEquals("Receipt must contain at least one item.", exception.getMessage());
    }
    
    @Test
    void shouldReturnPointsForReceipt() {
        UUID receiptId = UUID.randomUUID();
        when(receiptRepository.getPoints(receiptId)).thenReturn(Optional.of(100));

        Optional<Integer> points = receiptService.getPoints(receiptId);

        assertTrue(points.isPresent());
        assertEquals(100, points.get());
    }

    @Test
    void shouldThrowExceptionWhenPointsNotFound() {
        UUID receiptId = UUID.randomUUID();
        when(receiptRepository.getPoints(receiptId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            receiptService.getPoints(receiptId);
        });

        assertEquals("No points found for receipt ID: " + receiptId, exception.getMessage());
    }
}
