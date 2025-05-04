package com.bookstore.bookstoreapi.resources;

import com.bookstore.bookstoreapi.dao.OrderDAO;
import com.bookstore.bookstoreapi.dao.CartDAO;
import com.bookstore.bookstoreapi.dao.CustomerDAO;
import com.bookstore.bookstoreapi.model.Order;
import com.bookstore.bookstoreapi.exceptions.CartNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/customers/{customerId}/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    private OrderDAO orderDAO = new OrderDAO();
    private CartDAO cartDAO = new CartDAO();
    private CustomerDAO customerDAO = new CustomerDAO();

    //  POST /customers/{customerId}/orders
    @POST
    public Response placeOrder(@PathParam("customerId") int customerId) {

         
        if (!cartDAO.exists(customerId)) {
            throw new CartNotFoundException("Cart is empty or does not exist for customer ID " + customerId);
        }

        Order order = orderDAO.placeOrder(customerId);
        return Response.status(Response.Status.CREATED).entity(order).build();
    }

    //  GET /customers/{customerId}/orders
    @GET
    public List<Order> getAllOrders(@PathParam("customerId") int customerId) {
        
        if (!customerDAO.exists(customerId)) {
            throw new CartNotFoundException("Cart is empty or does not exist for customer ID " + customerId);
        }
        return orderDAO.getOrdersByCustomer(customerId);
    }

    // GET /customers/{customerId}/orders/{orderId}
    @GET
    @Path("/{orderId}")
    public Order getOrderById(@PathParam("customerId") int customerId,@PathParam("orderId") int orderId) {
        
        if (!customerDAO.exists(customerId)) {
            throw new CartNotFoundException("Cart is empty or does not exist for customer ID " + customerId);
        }

        Order order = orderDAO.getOrderById(customerId, orderId);
        return order;
    }
}
