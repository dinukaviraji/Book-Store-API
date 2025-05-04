
package com.bookstore.bookstoreapi.dao;

import com.bookstore.bookstoreapi.model.Author;
import com.bookstore.bookstoreapi.model.Book;
import com.bookstore.bookstoreapi.storage.DataStorage;
import java.util.*;
import java.util.stream.Collectors;

public class AuthorDAO {
       
    // Add a new author to the data storage
    public Author addAuthor(Author author) {
        author.setId(DataStorage.authorIdCounter++); // auto-increment author ID
        DataStorage.authors.put(author.getId(), author);
        return author;
    }
    
    // Get all authors from data storage
    public Collection<Author> getAllAuthors() {
        return DataStorage.authors.values();
    }
    
    // Get a specific author by ID
    public Author getAuthorById(int id) {
        return DataStorage.authors.get(id);
    }

    // Update author by ID
    public Author updateAuthor(int id, Author updatedAuthor) {
        updatedAuthor.setId(id);
        DataStorage.authors.put(id, updatedAuthor);
        return updatedAuthor;
    }

    // Delete author by ID
    public void deleteAuthor(int id) {
        DataStorage.authors.remove(id);
    }
    
    // Check if author exists
    public boolean exists(int id) {
        return DataStorage.authors.containsKey(id);
    }
    
    // Get all books written by a specific author
    public List<Book> getBooksByAuthorId(int id) {
    return DataStorage.books.values()
            .stream()
            .filter(book -> book.getAuthorId() == id)
            .collect(Collectors.toList());
    }
    
}
