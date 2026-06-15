package com.example.app;

import java.math.BigDecimal;
import java.util.List;

public class Main {

    public static void runPricingScenario() {
        PricingService pricingService = new PricingService();

        Customer standardCustomer = new Customer("Jo", CustomerType.STANDARD, 0);
        Customer premiumCustomer = new Customer("Premium Customer", CustomerType.PREMIUM, 100);
        Customer staffCustomer = new Customer("Staff Customer", CustomerType.STAFF, 150);

        Order standardOrder = new Order(
                standardCustomer,
                List.of(
                        new OrderItem("Notebook", new BigDecimal("10.00"), 2),
                        new OrderItem("Pen", new BigDecimal("2.50"), 3)
                ),
                false,
                null
        );

        Order premiumOrder = new Order(
                premiumCustomer,
                List.of(
                        new OrderItem("Backpack", new BigDecimal("40.00"), 1),
                        new OrderItem("Water Bottle", new BigDecimal("15.00"), 1)
                ),
                false,
                "SAVE10"
        );

        Order staffNextDayOrder = new Order(
                staffCustomer,
                List.of(
                        new OrderItem("Laptop Stand", new BigDecimal("30.00"), 2)
                ),
                true,
                "SAVE5"
        );

        printOrder("Standard order", pricingService, standardOrder);
        printOrder("Premium order", pricingService, premiumOrder);
        printOrder("Staff next-day order", pricingService, staffNextDayOrder);
    }

    private static void printOrder(String label, PricingService pricingService, Order order) {
        System.out.println("========== " + label + " ==========");
        System.out.println("Customer: " + order.getCustomer().getName());
        System.out.println("Customer type: " + order.getCustomer().getType());
        System.out.println("Loyalty points: " + order.getCustomer().getLoyaltyPoints());
        System.out.println("Next-day delivery: " + order.isNextDayDelivery());
        System.out.println("Discount code: " + order.getDiscountCode());

        System.out.println();

        System.out.println("Items:");
        for (OrderItem item : order.getItems()) {
            System.out.println("- " + item.getName()
                    + " | Unit price: £" + item.getUnitPrice()
                    + " | Quantity: " + item.getQuantity());
        }

        System.out.println();

        System.out.println("Subtotal: £" + pricingService.calculateSubtotal(order));
        System.out.println("Customer discount: £" + pricingService.calculateCustomerDiscount(order));
        System.out.println("Discount code amount: £" + pricingService.calculateDiscountCodeAmount(order));
        System.out.println("Delivery: £" + pricingService.calculateDelivery(order));
        System.out.println("Total: £" + pricingService.calculateTotal(order));

        System.out.println();

    }

    private static void fizzBuzz(int i) {

        /*
        "FizzBuzz" if i is divisible by 3 and 5,
        "Fizz" if i is divisible by 3,
        "Buzz" if i is divisible by 5
        "i" as a string, if none of the conditions are true.
         */

        System.out.print("fizzbuzz");
    }

    private static boolean anagramChecker(String word_1, String word_2) {
        /*
        Two words are parsed in
        Method returns true if the words are anagrams of eachother,
        and false if they are not
        E.g. 'listen' and 'silent' = true
         */
        return true;
    }

    public static void main(String[] args) {

        //run methods here

    }



}