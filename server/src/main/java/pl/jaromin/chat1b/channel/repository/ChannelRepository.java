package pl.jaromin.chat1b.channel.repository;

import pl.jaromin.chat1b.channel.entity.Channel;

public interface ChannelRepository {

    void save(Channel channel);

    boolean isPresent(String name);

    Channel findById(String name);

    void update(Channel channel);
}
