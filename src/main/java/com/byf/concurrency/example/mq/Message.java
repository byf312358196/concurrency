package com.byf.concurrency.example.mq;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Data
@Slf4j
public class Message {
    private Long id;
    private String msg;
    private Date sendTime;

    public void setSendTime(Date date){
        this.sendTime = date;
    }

    public Date getSendTime(){
        return (Date) this.sendTime.clone();
    }
}
