package com.byf.concurrency.example.mq;

import com.byf.concurrency.example.mq.kafka.KafkaSender;
import com.byf.concurrency.example.mq.rabbitmq.RabbitMQClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Controller
@RequestMapping("/mq")
public class MQController {
    @Resource
    private RabbitMQClient rabbitMQClient;

    @Resource
    private KafkaSender kafkaSender;

    @RequestMapping("/send")
    @ResponseBody
    public String send(@RequestParam("message") String message){
        rabbitMQClient.send(message);
        kafkaSender.send(message);
        return "SUCCESS";
    }
    public static void main(String[] args) {
        Message message = new Message();
        message.setSendTime(new Date());
        log.info("{}",message.getSendTime());
        message.getSendTime().setYear(2018);
        log.info("{}",message.getSendTime());
        message.getSendTime().setMonth(5);
        log.info("{}",message.getSendTime());
    }
}
