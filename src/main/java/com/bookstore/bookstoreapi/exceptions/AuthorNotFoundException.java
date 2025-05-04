package com.bookstore.bookstoreapi.exceptions;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(String message) {
        super(message); // Pass the message to the RuntimeException constructor
    }
}



