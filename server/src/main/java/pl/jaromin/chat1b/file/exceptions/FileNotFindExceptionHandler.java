package pl.jaromin.chat1b.file.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class FileNotFindExceptionHandler implements ExceptionMapper<FileNotFindException> {
    @Override
    public Response toResponse(FileNotFindException e) {
        return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
    }
}
