package pl.jaromin.chat1b.channel.exception;

public class NoAccessToChannelException extends RuntimeException{

    public NoAccessToChannelException(String channel) {
        super("You don't have access to channel: " + channel);
    }
}
