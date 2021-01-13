package com.example.activemq.topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;

/**
 * @author liugenghua
 **/
public class TopicSubscriber2 {

    public static void main(String[] args) {
        Topic topic = new ActiveMQTopic("test.topic");
        testTopic(topic);
    }

    /**
     * 消费者监听test.topic，消费接收到的消息
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
            MessageConsumer consumer = session.createConsumer(topic);
            MessageListener listener = new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    System.out.println(" => receive from " + topic.toString() + ": " + message);
                }
            };
            consumer.setMessageListener(listener);
            System.in.read();
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
