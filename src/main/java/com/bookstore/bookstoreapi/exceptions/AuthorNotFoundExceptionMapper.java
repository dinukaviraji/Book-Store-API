package com.bookstore.bookstoreapi.exceptions;

import javax.ws.rs.core.*;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthorNotFoundExceptionMapper implements ExceptionMapper<AuthorNotFoundException> {

    @Override
    public Response toResponse(AuthorNotFoundException exception) {
        
        // Create a JSON error message to return to the client
        String errorMessage = "{ \"error\": \"Author Not Found\", \"message\": \"" + exception.getMessage() + "\" }";

        // Return a 404 Not Found response with the custom error message
        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
