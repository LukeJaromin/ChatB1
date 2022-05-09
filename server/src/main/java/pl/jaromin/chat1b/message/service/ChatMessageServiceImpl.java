package pl.jaromin.chat1b.message.service;

import lombok.extern.java.Log;
import pl.jaromin.chat1b.channel.entity.Channel;
import pl.jaromin.chat1b.channel.exception.ChannelDoesNotExistException;
import pl.jaromin.chat1b.channel.exception.NoAccessToChannelException;
import pl.jaromin.chat1b.channel.repository.ChannelRepository;
import pl.jaromin.chat1b.jms.JmsMessageDto;
import pl.jaromin.chat1b.jms.JmsMessageService;
import pl.jaromin.chat1b.jms.MessageType;
import pl.jaromin.chat1b.message.dto.ChannelMessageDto;
import pl.jaromin.chat1b.message.dto.ChannelHistoryDto;
import pl.jaromin.chat1b.message.dto.PrivateMessageDto;
import pl.jaromin.chat1b.message.dto.PublicMessageDto;
import pl.jaromin.chat1b.message.mapper.ChatMessageMapper;
import pl.jaromin.chat1b.message.repository.ChatMessageRepository;
import pl.jaromin.chat1b.user.entity.ChatUser;
import pl.jaromin.chat1b.user.exception.NotAuthorizedException;
import pl.jaromin.chat1b.user.repository.UserRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Log
public class ChatMessageServiceImpl implements ChatMessageService {

    @Inject
    ChatMessageRepository chatMessageRepository;

    @Inject
    ChannelRepository channelRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    JmsMessageService jmsMessageService;

    @Override
    @Transactional
    public void send(PublicMessageDto publicMessageDto) {
        getUser(publicMessageDto.getSender());
        chatMessageRepository.save(ChatMessageMapper.INSTANCE.mapPublicToEntity(publicMessageDto));
        JmsMessageDto messageDto = JmsMessageDto.builder()
                .messageType(MessageType.PUBLIC)
                .text(publicMessageDto.getSender() + ": " + publicMessageDto.getMessage())
                .receivers(Collections.emptySet())
                .build();
        jmsMessageService.sendJmsMessage(messageDto);
    }

    @Override
    @Transactional
    public void sendToUser(PrivateMessageDto privateMessageDto) {
        getUser(privateMessageDto.getSender());
        getUser(privateMessageDto.getReceiver());
        chatMessageRepository.save(ChatMessageMapper.INSTANCE.mapPrivateToEntity(privateMessageDto));
        JmsMessageDto messageDto = JmsMessageDto.builder()
                .messageType(MessageType.PRIVATE)
                .text("Private message from " + privateMessageDto.getSender() + ": " + privateMessageDto.getMessage())
                .receivers(Set.of(privateMessageDto.getReceiver()))
                .build();
        jmsMessageService.sendJmsMessage(messageDto);
    }

    @Override
    @Transactional
    public void sendToChannel(ChannelMessageDto channelMessageDto) {
        chatMessageRepository.save(ChatMessageMapper.INSTANCE.mapChannelToEntity(channelMessageDto));
        Channel channel = getChannel(channelMessageDto.getChannel());
        Set<String> channelUsers = channel.getChatUsers().stream()
                .map(ChatUser::getName)
                .collect(Collectors.toSet());
        JmsMessageDto messageDto = JmsMessageDto.builder()
                .messageType(MessageType.CHANNEL)
                .text(String.format("Message on channel %s from %s: %s", channelMessageDto.getChannel(), channelMessageDto.getSender(), channelMessageDto.getMessage()))
                .receivers(channelUsers)
                .build();
        jmsMessageService.sendJmsMessage(messageDto);
    }

    @Override
    @Transactional
    public List<ChannelHistoryDto> getHistory(String channelName, String userName) {
        ChatUser user = getUser(userName);
        Channel channel = getChannel(channelName);
        checkIfUserHasAccessToChannel(channel, user);
        return chatMessageRepository.getHistory(channelName, userName).stream()
                .map(ChatMessageMapper.INSTANCE::mapEntityToHistory)
                .collect(Collectors.toList());
    }

    private Channel getChannel(String channelName) {
        Channel channel = channelRepository.findById(channelName);
        if (channel == null) {
            throw new ChannelDoesNotExistException(channelName);
        }
        return channel;
    }

    private ChatUser getUser(String username) {
        ChatUser user = userRepository.findById(username);
        if (user == null) {
            throw new NotAuthorizedException();
        }
        return user;
    }

    private void checkIfUserHasAccessToChannel(Channel channel, ChatUser user) {
        if (!channel.getChatUsers().contains(user)) {
            throw new NoAccessToChannelException(channel.getName());
        }
    }

}
