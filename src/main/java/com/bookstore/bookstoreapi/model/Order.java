
package com.bookstore.bookstoreapi.model;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private final int id;
    private final int customerId;
    private final LocalDate orderDate;
    private final List<CartItem> items;
    private final double totalAmount;

    // Parameterized constructor
    public Order(int id, int customerId, LocalDate orderDate, List<CartItem> items, double totalAmount) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.items = items;
        this.totalAmount = totalAmount;
    }

    // Getters for get the value 
    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    
    
    
}
