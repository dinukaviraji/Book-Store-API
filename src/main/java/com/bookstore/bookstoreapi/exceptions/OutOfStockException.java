
package com.bookstore.bookstoreapi.exceptions;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException(String message) {
        super(message); // Pass the message to the RuntimeException constructor
    }
}
