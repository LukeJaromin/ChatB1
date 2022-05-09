package pl.jaromin.chat1b.channel.service;

import pl.jaromin.chat1b.channel.entity.Channel;
import pl.jaromin.chat1b.channel.exception.ChannelAlreadyExistsException;
import pl.jaromin.chat1b.channel.exception.ChannelDoesNotExistException;
import pl.jaromin.chat1b.channel.exception.NoAccessToChannelException;
import pl.jaromin.chat1b.channel.repository.ChannelRepository;
import pl.jaromin.chat1b.user.entity.ChatUser;
import pl.jaromin.chat1b.user.exception.NotAuthorizedException;
import pl.jaromin.chat1b.user.exception.UserAlreadyExistsException;
import pl.jaromin.chat1b.user.exception.UserDoesNotExistException;
import pl.jaromin.chat1b.user.repository.UserRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

public class ChannelServiceImpl implements ChannelService {

    @Inject
    ChannelRepository channelRepository;

    @Inject
    UserRepository userRepository;

    @Override
    @Transactional
    public String addUserToChannel(String channelName, String username, String sender) {
        ChatUser senderUser = getUser(sender);
        ChatUser chatUser = getUser(username);
        Channel channel = getChannel(channelName);
        if (!channel.getChatUsers().contains(senderUser)) {
            throw new NotAuthorizedException();
        }
        if (channel.getChatUsers().contains(chatUser)) {
            throw new UserAlreadyExistsException(username);
        }
        channel.getChatUsers().add(chatUser);
        channelRepository.update(channel);
        return "User: " + username + " successfully added to channel: " + channelName;
    }

    @Override
    @Transactional
    public String removeUserFromChannel(String channelName, String username) {
        ChatUser user = getUser(username);
        Channel channel = getChannel(channelName);
        if (!channel.getChatUsers().contains(user)) {
            throw new NoAccessToChannelException(channelName);
        }
        channel.getChatUsers().remove(user);
        channelRepository.update(channel);
        return "User: " + username + " successfully removed from channel: " + channelName;
    }

    @Override
    @Transactional
    public String createChannel(String channelName, String username) {
        ChatUser user = getUser(username);
        if (channelRepository.isPresent(channelName)) {
            throw new ChannelAlreadyExistsException(channelName);
        }
        Channel channel = new Channel();
        channel.setName(channelName);
        channel.setChatUsers(new HashSet<>(Set.of(user)));
        channelRepository.save(channel);
        return String.format("Channel: %s created successfully.", channel.getName());
    }

    private ChatUser getUser(String username) {
        ChatUser user = userRepository.findById(username);
        if (user == null) {
            throw new UserDoesNotExistException(username);
        }
        return user;
    }

    private Channel getChannel(String name) {
        Channel channel = channelRepository.findById(name);
        if (channel == null) {
            throw new ChannelDoesNotExistException(name);
        }
        return channel;
    }
}
