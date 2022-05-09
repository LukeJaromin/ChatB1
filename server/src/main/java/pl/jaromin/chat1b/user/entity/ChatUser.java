package pl.jaromin.chat1b.user.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.jaromin.chat1b.channel.entity.Channel;
import pl.jaromin.chat1b.message.entity.ChatMessage;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Data
public class ChatUser {

    @Id
    private String name;

    @OneToMany(mappedBy = "sender")
    @EqualsAndHashCode.Exclude
    private Set<ChatMessage> sendMessages;

    @OneToMany(mappedBy = "receiver")
    @EqualsAndHashCode.Exclude
    private Set<ChatMessage> receivedMessages;

    @ManyToMany(mappedBy = "chatUsers")
    @EqualsAndHashCode.Exclude
    private Set<Channel> channels;

}
