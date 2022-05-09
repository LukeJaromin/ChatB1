package pl.jaromin.chat1b.user.repository;


import pl.jaromin.chat1b.user.entity.ChatUser;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@Singleton
@Transactional
public class JpaUserRepository implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(ChatUser chatUser) {
        entityManager.persist(chatUser);
    }

    @Override
    public boolean isPresent(String username) {
        return Optional.ofNullable(entityManager.find(ChatUser.class, username)).isPresent();
    }

    @Override
    public ChatUser findById(String username) {
        ChatUser chatUser = entityManager.find(ChatUser.class, username);
        return chatUser;
    }

}
