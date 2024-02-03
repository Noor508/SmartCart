package com.example.devilapplication.API.POST;


public class Model {
    private String name;
    private String price;
    private String id;
    private String createdAt;

    public Model(String name, String price, String id, String createdAt) {
        this.name = name;
        this.price = price;
        this.id = id;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}