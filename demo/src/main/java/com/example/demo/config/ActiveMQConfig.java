package com.example.demo.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableJms
public class ActiveMQConfig {


    /**
     * 缺陷  只能监听topic或者queue 不能同时消费两种类型的消息  待改进
     * 修改application.properties中的spring.jms.pub-sub-domain属性即可更换监听类型
     * @param url
     * @return
     */
    @Bean
    public ActiveMQConnectionFactory factory(@Value("${spring.activemq.broker-url}") String url){
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
        // 设置处理机制
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        redeliveryPolicy.setMaximumRedeliveries(6); // 消息处理失败重新处理次数
        factory.setRedeliveryPolicy(redeliveryPolicy);
        return factory;
    }
}