package com.example.test_mulitple_schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableScheduling
@SpringBootApplication
@RestController
public class RabbitMqSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqSampleApplication.class, args);
    }

    @RequestMapping("/get")
    public String get() {
        return String.valueOf(ScheduleConfig.index.get());
    }

    @RequestMapping("/reset")
    public String reset() {
        ScheduleConfig.index.set(0);
        return String.valueOf(ScheduleConfig.index.get());
    }
}
