package pl.jaromin.chat1b.user.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UserAlreadyExistsExceptionHandler implements ExceptionMapper<UserAlreadyExistsException> {

    @Override
    public Response toResponse(UserAlreadyExistsException e) {
        return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
    }
}
