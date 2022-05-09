package pl.jaromin.chat1b.channel.exception;

public class ChannelAlreadyExistsException extends RuntimeException {
    public ChannelAlreadyExistsException( String username) {
        super("Channel already exists: " + username);
    }
}
