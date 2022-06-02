package com.example.test_mulitple_schedule;

import com.example.test_mulitple_schedule.jpa.EmployeesService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ScheduleConfig {
    private final RabbitTemplate rabbitTemplate;
    private final EmployeesService service;
    public static AtomicInteger index = new AtomicInteger(0);

    @Autowired
    public ScheduleConfig(EmployeesService service, RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.service = service;
    }

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        return taskScheduler;
    }

    @Scheduled(cron = "${cron.expression}")
    public void doTask1() {
        System.out.println("doTask1 thread id:" + Thread.currentThread().getName());
        System.out.println(index.incrementAndGet());
        rabbitTemplate.convertAndSend(RabbitAdminConfig.EXCHANGE, RabbitAdminConfig.ROUTING_KET, "Hi");
    }

    @RabbitListener(queues = RabbitAdminConfig.QUEUE)
    public void consumeMessage(String message) {
        System.out.println(message);
    }
}
