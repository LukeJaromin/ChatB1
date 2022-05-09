package pl.jaromin.chat1b.channel.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoAccessToChannelExceptionHandler implements ExceptionMapper<NoAccessToChannelException> {

    @Override
    public Response toResponse(NoAccessToChannelException e) {
        return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
    }
}
