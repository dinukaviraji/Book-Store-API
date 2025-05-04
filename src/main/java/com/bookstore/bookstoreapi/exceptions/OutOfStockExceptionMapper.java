
package com.bookstore.bookstoreapi.exceptions;

import javax.ws.rs.core.*;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class OutOfStockExceptionMapper implements ExceptionMapper<OutOfStockException> {

    @Override
    public Response toResponse(OutOfStockException exception) {
        // Create a JSON error message to return to the client
       String errorMessage = "{ \"error\": \"Out Of Stock\", \"message\": \"" + exception.getMessage() + "\" }";

        return Response.status(Response.Status.CONFLICT).entity(errorMessage).type(MediaType.APPLICATION_JSON).build();
    }
}