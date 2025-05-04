
package com.bookstore.bookstoreapi.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message) {
        super(message); // Pass the message to the RuntimeException constructor
    }
}
