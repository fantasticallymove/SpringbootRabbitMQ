package com.example.test_mulitple_schedule;

import com.alibaba.fastjson.JSONObject;
import com.example.test_mulitple_schedule.jpa.EmployeesService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    private final EmployeesService service;

    @Autowired
    public Receiver(EmployeesService service) {
        this.service = service;
    }

    @RabbitListener(queues = RabbitAdminConfig.QUEUE)
    public void receive(String message){
        System.out.println(message);
        System.out.println("thread id:" + Thread.currentThread().getName());
        JSONObject jsonObject = JSONObject.parseObject(message);
        service.save(jsonObject.getString("name"));
    }
}
