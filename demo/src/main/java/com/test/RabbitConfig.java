package com.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sun.plugin.javascript.navig.Array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * @Company: www.shangpin.com
 * @Author: Raymond
 * @Date: 2018/12/26 16:42
 * @Description:
 */
//@Configuration
public class RabbitConfig {
    @Autowired
    private TestMq sender;
    @Bean
    public Queue Queue() {
        return new Queue("tian");
    }

    @Test
    public void hello() {
        sender.send();
    }

    @Test
    public void test(){
        int[] nums={4,5,1,3,45,97,32,22,39};

        /*1 for(int i=1;i<100;i++){
            for (int j=0;j<nums.length;j++){
                if (nums[j]!=i){
                    if (j==nums.length-1){
                        System.out.println("数组中没有："+i);
                    }
                    continue;
                }
                System.out.println("有:"+i);
                break;
            }
        }*/
        /*2 Map<String, Object> stringObjectHashMap = new HashMap<>();
        for (int num:nums){
            stringObjectHashMap.put(String.valueOf(num),"");
        }
        System.out.println("mapsize:"+stringObjectHashMap.size());
        for (int i=1;i<=100;i++){
            if (stringObjectHashMap.containsKey(String.valueOf(i))){
                System.out.println("有："+i);
            }else {
                System.out.println("没有:"+i);
            }
        }*/
        int[] numMax=new int[101];
    }
    public void bijiao(int indexNum,int minNumTemp,int index){
        if (indexNum<minNumTemp){
            System.out.println("没有:"+indexNum);
        }else if (indexNum==minNumTemp){
            System.out.println("有:"+indexNum);
        }
    }
}
