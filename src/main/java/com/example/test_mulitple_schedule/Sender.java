package com.example.test_mulitple_schedule;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public Sender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(){
        System.out.println("doTask1 thread id:" + Thread.currentThread().getName());
        rabbitTemplate.convertAndSend(RabbitAdminConfig.EXCHANGE, RabbitAdminConfig.ROUTING_KET, "{\"name:\""+"\"Joe\"}");
    }
}
