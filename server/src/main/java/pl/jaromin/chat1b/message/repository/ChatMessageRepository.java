package pl.jaromin.chat1b.message.repository;


import pl.jaromin.chat1b.message.entity.ChatMessage;

import java.util.List;

public interface ChatMessageRepository {

    void save(ChatMessage chatMessage);

    ChatMessage findById(long id);

    void delete(long id);

    List<ChatMessage> getHistory(String channelName, String user);
}
