package com.example.app;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PricingService {

    private static final BigDecimal STANDARD_DELIVERY = new BigDecimal("3.99");
    private static final BigDecimal NEXT_DAY_DELIVERY = new BigDecimal("7.99");

    public BigDecimal calculateSubtotal(Order order) {
        // BUG
        return order.getItems().stream()
                .map(OrderItem::getUnitPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateCustomerDiscount(Order order) {
        BigDecimal subtotal = calculateSubtotal(order);

        // Business rules:
        // STANDARD customers get no discount
        // PREMIUM customers get 10%
        // STAFF customers get 20%
        // Customers with 100+ loyalty points get an extra 5%

        BigDecimal discount = BigDecimal.ZERO;

        if (order.getCustomer().getType() == CustomerType.PREMIUM) {
            discount = discount.add(subtotal.multiply(new BigDecimal("0.05"))); // BUG: should be 10%
        }

        if (order.getCustomer().getType() == CustomerType.STAFF) {
            discount = discount.add(subtotal.multiply(new BigDecimal("0.10"))); // BUG: should be 20%
        }

        if (order.getCustomer().getLoyaltyPoints() > 100) { // BUG: should include exactly 100
            discount = discount.add(subtotal.multiply(new BigDecimal("0.05")));
        }

        return discount;
    }

    public BigDecimal calculateDelivery(Order order) {
        BigDecimal subtotal = calculateSubtotal(order);

        // Business rules:
        // Orders of £50 or more get free standard delivery
        // Next-day delivery is never free
        // Otherwise standard delivery is £3.99 and next-day delivery is £7.99

        if (subtotal.compareTo(new BigDecimal("50.00")) > 0) { // BUG: should include exactly £50
            return BigDecimal.ZERO;
        }

        if (order.isNextDayDelivery()) { // BUG: this check happens too late
            return NEXT_DAY_DELIVERY;
        }

        return STANDARD_DELIVERY;
    }

    public BigDecimal calculateDiscountCodeAmount(Order order) {
        BigDecimal subtotal = calculateSubtotal(order);

        // Business rules:
        // SAVE10 gives 10% off
        // SAVE5 gives £5 off orders over £25
        // Invalid or blank codes give no discount

        // BUG

        if (order.getDiscountCode() == null || order.getDiscountCode().isBlank()) {
            return BigDecimal.ZERO;
        }

        if (order.getDiscountCode().equals("SAVE10")) {
            return subtotal.multiply(new BigDecimal("0.01")); // BUG: should be 10%
        }

        if (order.getDiscountCode().equals("SAVE5") && subtotal.compareTo(new BigDecimal("25.00")) >= 0) {
            return new BigDecimal("10.00");
        }

        return BigDecimal.ZERO;
    }

    public BigDecimal calculateTotal(Order order) {
        BigDecimal subtotal = calculateSubtotal(order);
        BigDecimal customerDiscount = calculateCustomerDiscount(order);
        BigDecimal codeDiscount = calculateDiscountCodeAmount(order);
        BigDecimal delivery = calculateDelivery(order);

        BigDecimal total = subtotal
                .subtract(customerDiscount)
                .subtract(codeDiscount)
                .add(delivery);

        // BUG: total should never go below zero before delivery is added
        return total.setScale(2, RoundingMode.HALF_UP);
    }
}