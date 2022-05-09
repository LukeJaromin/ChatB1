package pl.jaromin.chat1b.user.controller;

import pl.jaromin.chat1b.user.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
public class UserController {

    @Inject
    private UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path(value = "/{username}")
    public Response saveUser(@PathParam("username") String username) {
        return Response.status(Response.Status.CREATED).entity(userService.saveUser(username)).build();
    }

    @GET
    @Path("/exists/{username}")
    public Response userExists(@PathParam("username") String username) {
        return Response.ok().entity(userService.userExists(username)).build();
    }
}
