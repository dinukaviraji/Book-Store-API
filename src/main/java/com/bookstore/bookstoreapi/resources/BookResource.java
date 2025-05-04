package com.bookstore.bookstoreapi.resources;

import com.bookstore.bookstoreapi.dao.AuthorDAO;
import com.bookstore.bookstoreapi.dao.BookDAO;
import com.bookstore.bookstoreapi.model.Book;
import com.bookstore.bookstoreapi.exceptions.BookNotFoundException;
import com.bookstore.bookstoreapi.exceptions.InvalidInputException;
import com.bookstore.bookstoreapi.exceptions.AuthorNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;
        

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    private final BookDAO bookDAO = new BookDAO();
    private final AuthorDAO authorDAO = new AuthorDAO();

    // Add a new book
    @POST
    public Response addBook(Book book) {
        if (book.getPublicationYear() > Calendar.getInstance().get(Calendar.YEAR)) {
            throw new InvalidInputException("Publication year cannot be in the future.");
        }
        if (!authorDAO.exists(book.getAuthorId())) {
            throw new AuthorNotFoundException("Author with ID " + book.getAuthorId() + " does not exist.");
        
        }
        Book createdBook = bookDAO.addBook(book);
        return Response.status(Response.Status.CREATED).entity(createdBook).build();
    }

    // Get all books
    @GET
    public Collection<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    // Get a specific book by ID
    @GET
    @Path("/{id}")
    public Book getBook(@PathParam("id") int id) {
        Book book = bookDAO.getBookById(id);
        if (book == null) {
            throw new BookNotFoundException("Book with ID " + id + " not found.");
        }
        return book;
    }

    // Update an existing book by ID
    @PUT
    @Path("/{id}")
    public Book updateBook(@PathParam("id") int id, Book updatedBook) {
        if (!bookDAO.exists(id)) {
            throw new BookNotFoundException("Book with ID " + id + " not found.");
        }
        return bookDAO.updateBook(id, updatedBook);
    }

    // Delete a book by ID
    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") int id) {
        if (!bookDAO.exists(id)) {
            throw new BookNotFoundException("Book with ID " + id + " not found.");
        }
        bookDAO.deleteBook(id);
        return Response.noContent().build();
    }
}
