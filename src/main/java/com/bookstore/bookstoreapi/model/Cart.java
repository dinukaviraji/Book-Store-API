package com.bookstore.bookstoreapi.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final int customerId;
    private List<CartItem> items = new ArrayList<>();

    // Parameterized constructor
    public Cart(int customerId) {
        this.customerId = customerId;
    }

    // Getters for get the value 
    public int getCustomerId() {
        return customerId;
    }

    public List<CartItem> getItems() {
        return items;
    }
    //Setters for change the value
    public void setItems(List<CartItem> items) {
        this.items = items;
    }
    
}
