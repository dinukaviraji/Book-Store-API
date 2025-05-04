
package com.bookstore.bookstoreapi.model;

public class CartItem {
    
    private int bookId;
    private int quantity;
    
    // Default constructor
    public CartItem() {}

    // Parameterized constructor
    public CartItem(int bookId, int quantity) {
        this.bookId = bookId;
        this.quantity = quantity;
    }

    // Getters for get the value 
    public int getBookId() {
        return bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    //Setters for change the value
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
