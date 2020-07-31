package com.example.demo.job.activeMQ;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
// 使用JmsListener配置消费者监听的队列，其中text是接收到的消息

    //http://127.0.0.1:8161  新建Queue:Firstqueue
    @JmsListener(destination = "springboot.test.queue")
    public void receiveQueue(String text) {
        System.out.println("Consumer收到的报文为:"+text);
        System.out.println("=================");
    }
    @JmsListener(destination = "springboot.test.topic")
    public void receiveTopic(String text) {
        System.out.println("Consumer收到的报文为:"+text);
        System.out.println("=================");
    }
}