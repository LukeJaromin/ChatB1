package pl.jaromin.chat1b.channel.controller;

import pl.jaromin.chat1b.channel.service.ChannelService;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("channel")
public class ChannelController {

    @Inject
    ChannelService channelService;

    @POST
    @Path("/{channel}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createChannel(@PathParam("channel") String channelName,
                                  @NotNull @HeaderParam("username") String username) {
        return Response.status(Response.Status.CREATED).entity(channelService.createChannel(channelName, username)).build();
    }

    @POST
    @Path("/{channel}/{user}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUserToChannel(@PathParam("channel") String channelName,
                                   @PathParam("user") String addedUserName,
                                   @NotNull @HeaderParam("username") String username) {
        return Response.ok().entity(channelService.addUserToChannel(channelName, addedUserName, username)).build();
    }

    @DELETE
    @Path("/leave/{channel}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeUserFromChannel(@PathParam("channel") String channelName,
                                        @NotNull @HeaderParam("username") String username) {
        return Response.ok(channelService.removeUserFromChannel(channelName, username)).build();
    }

}
