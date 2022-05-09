package pl.jaromin.chat1b.channel.service;

public interface ChannelService {
    String addUserToChannel(String channelName, String username, String sender);

    String removeUserFromChannel(String channelName, String username);

    String createChannel(String channelName, String username);
}
