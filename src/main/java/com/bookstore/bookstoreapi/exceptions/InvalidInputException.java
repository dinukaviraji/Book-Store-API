
package com.bookstore.bookstoreapi.exceptions;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message); // Pass the message to the RuntimeException constructor
    }
}
