package pl.jaromin.chat1b.message.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wildfly.common.annotation.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelMessageDto {

    private String sender;

    private String channel;

    private String message;
}
