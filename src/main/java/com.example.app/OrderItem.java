package com.example.app;

import java.math.BigDecimal;

public class OrderItem {

    private final String name;
    private final BigDecimal unitPrice;
    private final int quantity;

    public OrderItem(String name, BigDecimal unitPrice, int quantity) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }
}