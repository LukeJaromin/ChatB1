package pl.jaromin.chat1b.message.controller;

import pl.jaromin.chat1b.message.dto.ChannelHistoryDto;
import pl.jaromin.chat1b.message.dto.ChannelMessageDto;
import pl.jaromin.chat1b.message.dto.PrivateMessageDto;
import pl.jaromin.chat1b.message.dto.PublicMessageDto;
import pl.jaromin.chat1b.message.service.ChatMessageService;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("message")
public class MessageController {

    @Inject
    ChatMessageService chatMessageService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/channel/{channel}")
    public Response getHistory(@NotNull @PathParam("channel") String channelName,
                               @NotNull @HeaderParam("username") String userName) {
        List<ChannelHistoryDto> channelHistoryDto = chatMessageService.getHistory(channelName, userName);
        return Response.ok().entity(channelHistoryDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/public")
    public Response broadcast(PublicMessageDto publicMessageDto,
                              @NotNull @HeaderParam("username") String userName) {
        publicMessageDto.setSender(userName);
        chatMessageService.send(publicMessageDto);
        return Response.ok().build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/private")
    public Response sendPrivateMessage(PrivateMessageDto privateMessageDto,
                                       @NotNull @HeaderParam("username") String userName) {
        privateMessageDto.setSender(userName);
        chatMessageService.sendToUser(privateMessageDto);
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/channel")
    public Response sendChannelMessage(ChannelMessageDto channelMessageDto,
                                       @NotNull @HeaderParam("username") String userName) {
        channelMessageDto.setSender(userName);
        chatMessageService.sendToChannel(channelMessageDto);
        return Response.ok().build();
    }

}
