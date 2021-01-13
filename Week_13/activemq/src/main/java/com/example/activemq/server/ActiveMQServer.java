package com.example.activemq.server;

import org.apache.activemq.broker.BrokerService;

/**
 * @author liugenghua
 **/
public class ActiveMQServer {

    public static void main(String[] args) throws Exception {
        // 用java代码启动一个ActiveMQ broker server
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://localhost:61616");
        brokerService.start();
        System.in.read();
    }
}
