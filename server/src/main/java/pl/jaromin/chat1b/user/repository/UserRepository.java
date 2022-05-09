package pl.jaromin.chat1b.user.repository;

import pl.jaromin.chat1b.user.entity.ChatUser;

public interface UserRepository {

    void save(ChatUser chatUser);

    boolean isPresent(String username);

    ChatUser findById(String username);

}
