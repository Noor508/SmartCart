package com.example.devilapplication.RecyclerView;

public class Product {
    private int imageResourceId;
    private String price;
    private String description;

    public Product(int imageResourceId, String price, String description) {
        this.imageResourceId = imageResourceId;
        this.price = price;
        this.description = description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
