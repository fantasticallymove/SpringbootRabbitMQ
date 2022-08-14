package com.example.test_mulitple_schedule;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ScheduleConfig {
    public static AtomicInteger index = new AtomicInteger(0);

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
    }
}
