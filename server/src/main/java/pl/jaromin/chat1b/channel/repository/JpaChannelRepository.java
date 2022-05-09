package pl.jaromin.chat1b.channel.repository;

import pl.jaromin.chat1b.channel.entity.Channel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public class JpaChannelRepository implements ChannelRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Channel channel) {
        entityManager.persist(channel);
    }

    @Override
    public boolean isPresent(String name) {
        return Optional.ofNullable(entityManager.find(Channel.class, name)).isPresent();
    }

    @Override
    public Channel findById(String name) {
        return entityManager.find(Channel.class, name);
    }

    @Override
    public void update(Channel channel) {
        entityManager.merge(channel);
    }
}
