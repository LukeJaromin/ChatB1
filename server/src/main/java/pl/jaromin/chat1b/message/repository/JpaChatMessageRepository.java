package pl.jaromin.chat1b.message.repository;

import pl.jaromin.chat1b.message.entity.ChatMessage;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class JpaChatMessageRepository implements ChatMessageRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save(ChatMessage chatMessage) {
        entityManager.persist(chatMessage);
    }

    @Override
    public ChatMessage findById(long id) {
        return entityManager.find(ChatMessage.class, id);
    }

    @Override
    public void delete(long id) {
        Optional.ofNullable(entityManager.find(ChatMessage.class, id))
                .ifPresent(value -> entityManager.remove(value));
    }

    @Override
    public List<ChatMessage> getHistory(String channelName, String userName) {
        TypedQuery<ChatMessage> query = entityManager.createQuery("Select m from ChatMessage m where m.channel.name=:channel", ChatMessage.class);
        query.setParameter("channel", channelName);
        return query.getResultList();
    }
}
