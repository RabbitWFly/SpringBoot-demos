package com.rabbit.producer;

import com.rabbit.message.Demo01Message;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author rabbit
 * Date 2020/1/3
 * Description
 **/
@Component
public class Demo01Producer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public SendResult syncSend(Integer id){
        Demo01Message message = new Demo01Message();
        message.setId(id);
        //同步发送消息
        return rocketMQTemplate.syncSend(Demo01Message.TOPIC, message);
    }

    public void asyncSend(Integer id, SendCallback callback){
        Demo01Message message = new Demo01Message();
        message.setId(id);
        //异步发送消息
        rocketMQTemplate.asyncSend(Demo01Message.TOPIC, message, callback);
    }

    public void onewaySend(Integer id){
        Demo01Message message = new Demo01Message();
        message.setId(id);
        //oneway 发送消息
        rocketMQTemplate.sendOneWay(Demo01Message.TOPIC, message);
    }

}

