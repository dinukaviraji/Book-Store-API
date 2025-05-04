
package com.bookstore.bookstoreapi.dao;

import com.bookstore.bookstoreapi.model.Book;
import com.bookstore.bookstoreapi.storage.DataStorage;
import java.util.Collection;

public class BookDAO {
    
    public Book addBook(Book book) {
        book.setId(DataStorage.bookIdCounter++);
        DataStorage.books.put(book.getId(), book);
        return book;
    }

    public Collection<Book> getAllBooks() {
        return DataStorage.books.values();
    }

    public Book getBookById(int id) {
        return DataStorage.books.get(id);
    }

    public Book updateBook(int id, Book updatedBook) {
        updatedBook.setId(id);
        DataStorage.books.put(id, updatedBook);
        return updatedBook;
    }

    public void deleteBook(int id) {
        DataStorage.books.remove(id);
    }
    
    public boolean exists(int id) {
        return DataStorage.books.containsKey(id);
    }
}
