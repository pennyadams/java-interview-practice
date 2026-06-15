package com.example.app;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PricingServiceTest {

    private final PricingService pricingService = new PricingService();

    @Test
    void shouldCalculateSubtotalForSingleItem() {
        Customer customer = new Customer("Mike", CustomerType.STANDARD, 0);

        Order order = new Order(
                customer,
                List.of(new OrderItem("Notebook", new BigDecimal("10.00"), 1)),
                false,
                null
        );

        assertEquals(new BigDecimal("10.00"), pricingService.calculateSubtotal(order));
    }
}