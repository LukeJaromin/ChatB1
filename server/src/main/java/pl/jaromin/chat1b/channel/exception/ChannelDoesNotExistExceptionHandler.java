package pl.jaromin.chat1b.channel.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ChannelDoesNotExistExceptionHandler implements ExceptionMapper<ChannelDoesNotExistException> {

    @Override
    public Response toResponse(ChannelDoesNotExistException e) {
        return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
    }
}
