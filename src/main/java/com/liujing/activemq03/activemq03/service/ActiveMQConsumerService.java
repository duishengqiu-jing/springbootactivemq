package com.liujing.activemq03.activemq03.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ActiveMQConsumerService {

    @JmsListener(destination = "springboot", containerFactory = "jmsListenerContainerTopic")
    public void receive(Object msg) {
        //template已经帮我们做好了转换，只能接受string的
        System.out.println("receive:"+msg);
    }
}
