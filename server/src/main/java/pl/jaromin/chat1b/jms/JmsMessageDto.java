package pl.jaromin.chat1b.jms;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

@Builder
@Value
public class JmsMessageDto implements Serializable {

    static final long serialVersionUID = 7658779224323647563L;
    String text;
    Set<String> receivers;
    MessageType messageType;
}

