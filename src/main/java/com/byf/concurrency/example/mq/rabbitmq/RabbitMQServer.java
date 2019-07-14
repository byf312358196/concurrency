package com.byf.concurrency.example.mq.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMQServer {
    @RabbitListener(queues = {QueueConstants.TEST})
    public void receive(String message){
        log.info("rabbit mq receive:{}",message);

    }
}
