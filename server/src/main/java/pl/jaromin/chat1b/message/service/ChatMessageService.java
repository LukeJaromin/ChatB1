package pl.jaromin.chat1b.message.service;

import pl.jaromin.chat1b.message.dto.ChannelHistoryDto;
import pl.jaromin.chat1b.message.dto.ChannelMessageDto;
import pl.jaromin.chat1b.message.dto.PrivateMessageDto;
import pl.jaromin.chat1b.message.dto.PublicMessageDto;

import java.util.List;

public interface ChatMessageService {

    void send(PublicMessageDto publicMessageDto);

    void sendToUser(PrivateMessageDto privateMessageDto);

    void sendToChannel(ChannelMessageDto channelMessageDto);

    List<ChannelHistoryDto> getHistory(String channelName, String username);
}