package com.test;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Company: www.shangpin.com
 * @Author: Raymond
 * @Date: 2018/12/26 14:13
 * @Description:
 */
//@Component
public class TestMq {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("tian", context);
    }
}
