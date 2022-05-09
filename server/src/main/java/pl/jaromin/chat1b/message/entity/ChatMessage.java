package pl.jaromin.chat1b.message.entity;

import lombok.Data;
import pl.jaromin.chat1b.channel.entity.Channel;
import pl.jaromin.chat1b.user.entity.ChatUser;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
public class ChatMessage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long massageId;

    @ManyToOne
    private ChatUser sender;

    @ManyToOne
    private ChatUser receiver;

    @ManyToOne
    private Channel channel;

    private String message;

    private LocalDateTime date;

}
