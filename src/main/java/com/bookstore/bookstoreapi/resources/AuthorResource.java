package com.bookstore.bookstoreapi.resources;

import com.bookstore.bookstoreapi.dao.AuthorDAO;
import com.bookstore.bookstoreapi.model.Author;
import com.bookstore.bookstoreapi.model.Book;
import com.bookstore.bookstoreapi.storage.DataStorage;
import com.bookstore.bookstoreapi.exceptions.AuthorNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;
import java.util.stream.Collectors;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {

    private AuthorDAO authorDAO = new AuthorDAO();
    
    // Add a new author
    @POST
    public Response addAuthor(Author author) {
        Author createdAuthor = authorDAO.addAuthor(author);
        return Response.status(Response.Status.CREATED).entity(createdAuthor).build();
    }

    // Get all authors
    @GET
    public Collection<Author> getAllAuthors() {
        return authorDAO.getAllAuthors();
    }

    // Get a specific author by ID
    @GET
    @Path("/{id}")
    public Author getAuthor(@PathParam("id") int id) {
        Author author = authorDAO.getAuthorById(id);
        if (author == null) {
            throw new AuthorNotFoundException("Author with ID " + id + " not found.");
        }
        return author;
    }

    // Update an existing author by ID
    @PUT
    @Path("/{id}")
    public Author updateAuthor(@PathParam("id") int id, Author updatedAuthor) {
        if (!authorDAO.exists(id)) {
            throw new AuthorNotFoundException("Author with ID " + id + " not found.");
        }
        return authorDAO.updateAuthor(id, updatedAuthor);
    }

    // Delete an author by ID
    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") int id) {
        if (!authorDAO.exists(id)) {
            throw new AuthorNotFoundException("Author with ID " + id + " not found.");
        }
        authorDAO.deleteAuthor(id);
        return Response.noContent().build();
    }

    // Get all books written by a specific author
    @GET
    @Path("/{id}/books")
    public List<Book> getBooksByAuthor(@PathParam("id") int authorId) {
        if (!authorDAO.exists(authorId)) {
            throw new AuthorNotFoundException("Author with ID " + authorId + " not found.");
        }
        return DataStorage.books.values()
                .stream()
                .filter(book -> book.getAuthorId() == authorId)
                .collect(Collectors.toList());
    }
}

