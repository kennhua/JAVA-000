package com.example.activemq.topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;

/**
 * @author liugenghua
 **/
public class TopicPublisher {

    public static void main(String[] args) {
        Topic topic = new ActiveMQTopic("test.topic");
        testTopic(topic);
    }

    /**
     * 发送消息到test.topic
     *
     * @param topic
     */
    private static void testTopic(Topic topic) {
        ActiveMQConnection conn = null;
        Session session = null;
        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
            conn = (ActiveMQConnection) factory.createConnection();
            conn.start();
            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(topic);
            int index = 0;
            while (index++ < 100) {
                Thread.sleep(100);
                TextMessage message = session.createTextMessage(index + " message.");
                System.out.println("send message " + index);
                producer.send(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != session) {
                try {
                    session.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if (null != conn) {
                try {
                    conn.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
