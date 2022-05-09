package pl.jaromin.chat1b.jms;

import lombok.SneakyThrows;

import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Topic;

public class JmsMessageService {

    private static final String CONNECTION_FACTORY_JNDI_NAME = "jms/RemoteConnectionFactory";
    private static final String MESSAGES_TOPIC_JNDI_NAME = "jms/topic/Messages";

    @Inject
    ProxyFactory proxyFactory;

    @SneakyThrows
    public void sendJmsMessage(JmsMessageDto text) {
        ConnectionFactory connectionFactory = proxyFactory.createProxy(CONNECTION_FACTORY_JNDI_NAME);
        Topic topic = proxyFactory.createProxy(MESSAGES_TOPIC_JNDI_NAME);
        try (JMSContext context = connectionFactory.createContext()) {
            context.createProducer().send(topic, text);
        }
    }
}
