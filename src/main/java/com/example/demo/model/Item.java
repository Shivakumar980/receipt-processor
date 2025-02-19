package com.example.demo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Item {

    
    @NotBlank(message = "Item description cannot be blank")
    @Pattern(regexp = "^[\\w\\s\\-]+$", message = "Item description can contain alphanumeric characters, spaces, and hyphens only")
    private String shortDescription;

    @NotNull(message = "Price cannot be null")
    @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Price must be a valid decimal with two decimal places")
    private String price;

    public Item() {}

    public Item(String shortDescription, String price) {
        this.shortDescription = shortDescription;
        this.price = price;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
