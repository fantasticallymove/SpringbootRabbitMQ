package com.example.test_mulitple_schedule;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitAdminConfig implements BeanPostProcessor {
    public static final String EXCHANGE = "EXCHANGE";
    public static final String QUEUE = "ry";
    public static final String ROUTING_KET = "KEY";
    @Bean
    public Queue queue() {
        return new Queue(QUEUE, false);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KET);
    }


    /**
     * 初始化rabbitAdmin对象
     * 自動初始化Exchange,自動創建隊列
     * @param connectionFactory
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory, Queue queue, TopicExchange exchange) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        rabbitAdmin.declareExchange(exchange);
        rabbitAdmin.declareQueue(queue);
        return rabbitAdmin;
    }

    /**
     * 自動建立用戶端連線
     * @return
     */
    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory localhost = new CachingConnectionFactory();
        localhost.setHost("localhost");
        localhost.setPort(5672);
        localhost.setUsername("guest");
        localhost.setPassword("guest");
        //afterPropertiesSet()會自動被呼叫無須像Redis需要顯式呼叫
        return localhost;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }
}
