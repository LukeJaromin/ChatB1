package pl.jaromin.chat1b.channel.exception;

public class ChannelDoesNotExistException extends RuntimeException {

    public ChannelDoesNotExistException(String channelName) {
        super("Channel does not exist: " + channelName);
    }
}
