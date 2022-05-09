package pl.jaromin.chat1b.message.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrivateMessageDto {

    private String sender;

    private String message;

    private String receiver;
}
