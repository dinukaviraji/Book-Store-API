package com.bookstore.bookstoreapi.exceptions;

import javax.ws.rs.core.*;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomerNotFoundExceptionMapper implements ExceptionMapper<CustomerNotFoundException> {

    @Override
    public Response toResponse(CustomerNotFoundException exception) {
        // Create a JSON error message to return to the client
        String errorMessage = "{ \"error\": \"Customer Not Found\", \"message\": \"" + exception.getMessage() + "\" }";

        // Return a 404 Not Found response with the custom error message
        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}

