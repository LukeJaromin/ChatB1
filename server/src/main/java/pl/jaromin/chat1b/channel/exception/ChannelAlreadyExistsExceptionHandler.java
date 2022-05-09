package pl.jaromin.chat1b.channel.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ChannelAlreadyExistsExceptionHandler implements ExceptionMapper<ChannelAlreadyExistsException> {

    @Override
    public Response toResponse(ChannelAlreadyExistsException e) {
        return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
    }
}
