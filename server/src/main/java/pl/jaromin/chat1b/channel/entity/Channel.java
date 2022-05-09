package pl.jaromin.chat1b.channel.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.jaromin.chat1b.message.entity.ChatMessage;
import pl.jaromin.chat1b.user.entity.ChatUser;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Data
public class Channel {

    @Id
    private String name;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "Channel_User",
            joinColumns = {@JoinColumn(name = "CHANNEL_NAME")},
            inverseJoinColumns = {@JoinColumn(name = "USER_NAME")}
    )
    @EqualsAndHashCode.Exclude
    private Set<ChatUser> chatUsers;

}
