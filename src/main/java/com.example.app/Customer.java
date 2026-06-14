package com.example.app;

public class Customer {

    private final String name;
    private final CustomerType type;
    private final int loyaltyPoints;

    public Customer(String name, CustomerType type, int loyaltyPoints) {
        this.name = name;
        this.type = type;
        this.loyaltyPoints = loyaltyPoints;
    }

    public String getName() {
        return name;
    }

    public CustomerType getType() {
        return type;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }
}