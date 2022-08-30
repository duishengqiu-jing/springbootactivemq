package com.liujing.activemq03.activemq03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.ArrayList;

@Service
public class ActiveMQProducerService {
    //是JmsTemplate的一次包装
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(String destination, String msg) {
        ArrayList<String> list = new ArrayList<>();
        list.add("string1");
        list.add("string2");
        list.add("string3");
        //这个方法比较简单，想要复杂点的设置没办法设置
        jmsMessagingTemplate.convertAndSend(destination, list);
    }

    public void send2(String destination, String msg) throws JMSException {
//        ArrayList<String> list = new ArrayList<>();
//        list.add("string1");
//        list.add("string2");
//        list.add("string3");
        //复杂点的东西可以用jmsTemplate里面的
        //用原始的配置也可以，从拿到连接池开始
//        ConnectionFactory factory = jmsTemplate.getConnectionFactory();
//        Connection connection = factory.createConnection();
//        connection.start();
//        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        Destination queue = session.createQueue(destination);
//        MessageProducer producer = session.createProducer(queue);
//        MapMessage mapMessage = session.createMapMessage();
//        mapMessage.setString("ss","sss");
//        mapMessage.setInt("int", 22);
//        jmsTemplate.setDeliveryMode(DeliveryMode.PERSISTENT);
//        jmsTemplate.setDefaultDestination(queue);
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                //初始化一个想要的message出来，session之前的操作是template完成的
                //这个比较灵活
                TextMessage textMessage = session.createTextMessage("springboot");
                textMessage.setStringProperty("hello", "world");
                System.out.println("createMessage:"+textMessage.toString());
                return textMessage;
            }
        });
    }
}
