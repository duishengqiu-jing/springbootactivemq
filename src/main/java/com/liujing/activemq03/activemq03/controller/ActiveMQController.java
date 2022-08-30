package com.liujing.activemq03.activemq03.controller;

import com.liujing.activemq03.activemq03.service.ActiveMQConsumerService;
import com.liujing.activemq03.activemq03.service.ActiveMQProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;

@RestController
public class ActiveMQController {

    @Autowired
    private ActiveMQProducerService activeMQProducerService;

    @Autowired
    private ActiveMQConsumerService activeMQConsumerService;

    @GetMapping("send")
    public String send() throws JMSException {
        activeMQProducerService.send2("springboot", "springbootsdvwsf");
        return "ok";
    }

}
