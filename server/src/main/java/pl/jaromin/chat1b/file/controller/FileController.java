package pl.jaromin.chat1b.file.controller;

import pl.jaromin.chat1b.file.dto.DownloadFileDto;
import pl.jaromin.chat1b.file.dto.UploadFileDto;
import pl.jaromin.chat1b.file.service.FileService;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

@Path("file")
public class FileController {

    @Inject
    FileService fileService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String uploadFile(UploadFileDto uploadFileDto,
                             @NotNull @HeaderParam("username") String userName) {
        uploadFileDto.setSender(userName);
        return fileService.uploadFile(uploadFileDto);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadFile(@PathParam("id") long fileId, @QueryParam("receiver") String receiver) {
        DownloadFileDto file = fileService.downloadFile(fileId, receiver);
        if (file == null) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "File doesn't exist or you don't have access.").build();
        }
        StreamingOutput stream = output -> output.write(file.getBytes());
        return Response.ok(stream, MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "inline; filename=\"" + file.getName() + "\"")
                .encoding("utf-8")
                .build();
    }
}
