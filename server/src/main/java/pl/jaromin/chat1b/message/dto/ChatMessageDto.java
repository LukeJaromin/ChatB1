package pl.jaromin.chat1b.message.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ChatMessageDto {

    private String text;

    private String sender;

    private String receiver;

    private String channel;
}
