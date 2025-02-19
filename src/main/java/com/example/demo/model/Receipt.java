package com.example.demo.model;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Receipt {

    private UUID id;

    @NotBlank(message = "Retailer name cannot be blank")
    @Pattern(regexp = "^[\\w\\s\\-&]+$", message = "Retailer name must be alphanumeric, spaces, hyphens, or ampersands only")
    private String retailer;

    @NotNull(message = "Purchase date cannot be null")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Purchase date must be in YYYY-MM-DD format")
    private String purchaseDate;

    @NotNull(message = "Purchase time cannot be null")
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d$", message = "Purchase time must be in HH:mm 24-hour format")
    private String purchaseTime;
    
    @NotEmpty(message = "Items list cannot be empty")
    private List<@Valid Item> items; // Nested validation

    @NotNull(message = "Total amount cannot be null")
    @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Total must be a valid decimal with two decimal places")
    private String total;

    public Receipt() {
        this.id = UUID.randomUUID(); // Generate unique ID
    }

    public Receipt(String retailer, String purchaseDate, String purchaseTime, List<Item> items, String total) {
        this.id = UUID.randomUUID();
        this.retailer = retailer;
        this.purchaseDate = purchaseDate;
        this.purchaseTime = purchaseTime;
        this.items = items;
        this.total = total;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
