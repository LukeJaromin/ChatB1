package pl.jaromin.chat1b.client;

import pl.jaromin.chat1b.client.channel.ChannelWorker;
import pl.jaromin.chat1b.client.message.MessageWorker;

public class ConsoleMessageReader {

    public static void readMessage(Action action, String username) {
        switch (action) {
            case ADD_USER_TO_CHANNEL:
                ChannelWorker.addUserToChannel(username);
                break;
            case CREATE_CHANNEL:
                ChannelWorker.createChannel(username);
                break;
            case LEAVE_CHANNEL:
                ChannelWorker.leaveChannel(username);
                break;
            case PRIVATE_MESSAGE:
                MessageWorker.sendToUser(username);
                break;
            case CHANNEL_MESSAGE:
                MessageWorker.broadcastToChannel(username);
                break;
            case PUBLIC_MESSAGE:
                MessageWorker.broadcast(username);
                break;
            case SEND_FILE:
                MessageWorker.sendFile(username);
                break;
            case HISTORY_CHANNEL:
                MessageWorker.getHistoryFromChannel(username);
                break;
        }
    }
}
