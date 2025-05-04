
package com.bookstore.bookstoreapi.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super(message); // Pass the message to the RuntimeException constructor
    }
}    
    


