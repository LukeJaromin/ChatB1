package pl.jaromin.chat1b.message.dto;

import lombok.Data;

@Data
public class ChannelHistoryDto {

    private String sender;

    private String channel;

    private String message;
}
