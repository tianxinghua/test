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
    private AmqpTemplate amqpTemplate;
    public void send(){
        String context="helloMQ"+new Date();
        System.out.println("send:"+context);
        amqpTemplate.convertAndSend("tian",context);
    }
}
