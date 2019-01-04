package com.test;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Company: www.shangpin.com
 * @Author: Raymond
 * @Date: 2018/12/26 16:38
 * @Description:
 */
//@Component
@RabbitListener(queues = "tian")
public class Receiver {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver  : " + hello);
    }
}
