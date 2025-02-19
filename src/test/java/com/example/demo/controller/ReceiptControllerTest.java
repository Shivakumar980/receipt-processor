package com.example.demo.controller;

import com.example.demo.model.Item;
import com.example.demo.model.Receipt;
import com.example.demo.service.ReceiptService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class ReceiptControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ReceiptService receiptService;

    @InjectMocks
    private ReceiptController receiptController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(receiptController).build();
    }

    @Test
    public void testProcessReceipt() throws Exception {
       
        UUID receiptId = UUID.randomUUID();
    
       
        Item item = new Item("Laptop", "999.99");
        List<Item> items = new ArrayList<>();
        items.add(item);  
    
        
        Receipt receipt = new Receipt("Walmart", "2025-02-15", "12:30", items, "999.99");
    
       
        when(receiptService.processReceipt(any(Receipt.class))).thenReturn(receiptId);
    
        mockMvc.perform(post("/receipts/process")
                .contentType("application/json")
                .content("{\"retailer\":\"Walmart\",\"purchaseDate\":\"2025-02-15\",\"purchaseTime\":\"12:30\",\"total\":\"999.99\",\"items\":[{\"shortDescription\":\"Laptop\",\"price\":\"999.99\"}]}")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value("Receipt saved with ID: " + receiptId.toString()));
    }
    

    @Test
    public void testGetReceiptPoints_Success() throws Exception {
       
        UUID receiptId = UUID.randomUUID();
        when(receiptService.getPoints(receiptId)).thenReturn(Optional.of(100));

     
        mockMvc.perform(get("/receipts/" + receiptId + "/points"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(100));
    }

    @Test
    public void testGetReceiptPoints_NotFound() throws Exception {
      
        UUID receiptId = UUID.randomUUID();
        when(receiptService.getPoints(receiptId)).thenReturn(Optional.empty());

       
        mockMvc.perform(get("/receipts/" + receiptId + "/points"))
                .andExpect(status().isNotFound());
    }
}
