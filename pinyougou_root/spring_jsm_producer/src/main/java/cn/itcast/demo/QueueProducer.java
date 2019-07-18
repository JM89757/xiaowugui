package cn.itcast.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;


@Component
public class QueueProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Destination queueTextDestination;

    /* public void sendTextMessage(final String text) {
         jmsTemplate.send(queueTextDestination, new MessageCreator() {
             public Message createMessage(Session session) throws JMSException {
                 return session.createTextMessage(text);
             }
         });
     }*/
    public void sendTextMessage(String text) {
        jmsTemplate.send(queueTextDestination, (session -> session.createTextMessage(text)));
    }
}
