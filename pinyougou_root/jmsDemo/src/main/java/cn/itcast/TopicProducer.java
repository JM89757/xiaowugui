package cn.itcast;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicProducer {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("test-topic");
        MessageProducer producer = session.createProducer(topic);
        TextMessage textMessage = session.createTextMessage("Welcome to pinyougou!");
        producer.send(textMessage);
        producer.close();
        session.close();
        connection.close();
    }
}
