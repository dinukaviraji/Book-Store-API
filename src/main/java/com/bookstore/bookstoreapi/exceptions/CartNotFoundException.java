
package com.bookstore.bookstoreapi.exceptions;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(String message) {
        super(message); // Pass the message to the RuntimeException constructor
    }
}
