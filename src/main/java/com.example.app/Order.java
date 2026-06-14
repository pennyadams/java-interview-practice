package com.example.app;

import java.util.List;

public class Order {

    private final Customer customer;
    private final List<OrderItem> items;
    private final boolean nextDayDelivery;
    private final String discountCode;

    public Order(Customer customer, List<OrderItem> items, boolean nextDayDelivery, String discountCode) {
        this.customer = customer;
        this.items = items;
        this.nextDayDelivery = nextDayDelivery;
        this.discountCode = discountCode;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public boolean isNextDayDelivery() {
        return nextDayDelivery;
    }

    public String getDiscountCode() {
        return discountCode;
    }
}