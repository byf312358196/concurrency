package com.byf.concurrency.example.mq.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class RabbitMQClient {
    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send(String message){
        log.info("rabbit mq send:{}", message);
        rabbitTemplate.convertAndSend(QueueConstants.TEST,message);
    }
}
