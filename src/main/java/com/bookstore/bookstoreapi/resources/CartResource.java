package com.bookstore.bookstoreapi.resources;

import com.bookstore.bookstoreapi.dao.CartDAO;
import com.bookstore.bookstoreapi.dao.BookDAO;
import com.bookstore.bookstoreapi.dao.CustomerDAO;
import com.bookstore.bookstoreapi.model.CartItem;
import com.bookstore.bookstoreapi.model.Book;
import com.bookstore.bookstoreapi.exceptions.BookNotFoundException;
import com.bookstore.bookstoreapi.exceptions.CartNotFoundException;
import com.bookstore.bookstoreapi.exceptions.CustomerNotFoundException;
import com.bookstore.bookstoreapi.exceptions.OutOfStockException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/customers/{customerId}/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {

    private final CartDAO cartDAO = new CartDAO();
    private final BookDAO bookDAO = new BookDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();

    // POST /customers/{customerId}/cart/items
    @POST
    @Path("/items")
    public Response addItemToCart(@PathParam("customerId") int customerId, CartItem item) {
        if (!customerDAO.exists(customerId)) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
        }
        
        Book bookId = bookDAO.getBookById(item.getBookId());
        if (bookId == null) {
            throw new BookNotFoundException("Book with ID " + item.getBookId() + " not found.");
        }
        
        if (bookId.getStock() < item.getQuantity()) {
            throw new OutOfStockException("Not enough stock available for Book ID " + item.getBookId());
        }

        cartDAO.addItemToCart(customerId, item);
        return Response.status(Response.Status.CREATED).entity(item).build();
    }

    // GET /customers/{customerId}/cart
    @GET
    public List<CartItem> getCart(@PathParam("customerId") int customerId) {
        List<CartItem> cart = cartDAO.getCart(customerId);
        if (cart == null) {
            throw new CartNotFoundException("Cart for customer ID " + customerId + " not found.");
        }
        return cart;
    }

    // PUT /customers/{customerId}/cart/items/{bookId}
    @PUT
    @Path("/items/{bookId}")
    public Response updateCartItem(@PathParam("customerId") int customerId,
                                   @PathParam("bookId") int bookId,
                                   CartItem updatedItem) {
        Book book = bookDAO.getBookById(bookId);
        if (book == null) {
            throw new BookNotFoundException("Book with ID " + bookId + " not found.");
        }
        if (book.getStock() < updatedItem.getQuantity()) {
            throw new OutOfStockException("Not enough stock available for Book ID " + bookId);
        }

        cartDAO.updateCartItem(customerId, updatedItem);
        return Response.ok(updatedItem).build();
    }

    // DELETE /customers/{customerId}/cart/items/{bookId}
    @DELETE
    @Path("/items/{bookId}")
    public Response removeItemFromCart(@PathParam("customerId") int customerId,
                                       @PathParam("bookId") int bookId) {
        cartDAO.removeItemFromCart(customerId, bookId);
        return Response.noContent().build();
    }
}

