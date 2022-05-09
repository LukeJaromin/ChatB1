package pl.jaromin.chat1b.client;

import pl.jaromin.chat1b.client.user.UserWorker;
import pl.jaromin.chat1b.jms.JmsMessageDto;
import pl.jaromin.chat1b.jms.MessageType;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.Topic;
import javax.naming.NamingException;
import java.util.Scanner;

public class Client {

    private static final String CONNECTION_FACTORY_JNDI_NAME = "jms/RemoteConnectionFactory";
    private static final String MESSAGES_TOPIC_JNDI_NAME = "jms/topic/Messages";
    private static String username;


    private static MessageListener onMessage = message -> {
        try {
            JmsMessageDto messageDto = message.getBody(JmsMessageDto.class);
            if (clientShouldReceiveMessage(messageDto)) {
                System.out.println((messageDto.getText()));
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    };

    private static boolean clientShouldReceiveMessage(JmsMessageDto messageDto) {
        return messageDto.getMessageType() == MessageType.PUBLIC
                || messageDto.getReceivers().contains(username);
    }


    public static void main(String[] args) throws NamingException {
        username = UserWorker.getUsername();
        ProxyFactory proxyFactory = new ProxyFactory();
        ConnectionFactory connectionFactory = proxyFactory.createProxy(CONNECTION_FACTORY_JNDI_NAME);
        Topic topic = proxyFactory.createProxy(MESSAGES_TOPIC_JNDI_NAME);
        try (JMSContext jmsContext = connectionFactory.createContext();
             JMSConsumer consumer = jmsContext.createConsumer(topic)) {
            consumer.setMessageListener(onMessage);
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                try {
                    Action action = Action.valueOf(scanner.nextLine());
                    ConsoleMessageReader.readMessage(action, username);
                } catch (IllegalArgumentException exception) {
                    System.out.println("Polecenie nieznane");
                }
            }
        }
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            Action action = Action.valueOf(scanner.nextLine());
            ConsoleMessageReader.readMessage(action, username);
        }
    }
}
