package com.bookstore.bookstoreapi.resources;

import com.bookstore.bookstoreapi.dao.CustomerDAO;
import com.bookstore.bookstoreapi.model.Customer;
import com.bookstore.bookstoreapi.exceptions.CustomerNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Collection;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    private CustomerDAO customerDAO = new CustomerDAO();

    // Add a new customer
    @POST
    public Response addCustomer(Customer customer) {
        Customer createdCustomer = customerDAO.addCustomer(customer);
        return Response.status(Response.Status.CREATED).entity(createdCustomer).build();
    }

    // Get all customers
    @GET
    public Collection<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    // Get a specific customer by ID
    @GET
    @Path("/{id}")
    public Customer getCustomer(@PathParam("id") int id) {
        Customer customer = customerDAO.getCustomerById(id);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with ID " + id + " not found.");
        }
        return customer;
    }

    // Update an existing customer by ID
    @PUT
    @Path("/{id}")
    public Customer updateCustomer(@PathParam("id") int id, Customer updatedCustomer) {
        if (!customerDAO.exists(id)) {
            throw new CustomerNotFoundException("Customer with ID " + id + " not found.");
        }
        return customerDAO.updateCustomer(id, updatedCustomer);
    }

    // Delete a customer by ID
    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") int id) {
        if (!customerDAO.exists(id)) {
            throw new CustomerNotFoundException("Customer with ID " + id + " not found.");
        }
        customerDAO.deleteCustomer(id);
        return Response.noContent().build();
    }
}

