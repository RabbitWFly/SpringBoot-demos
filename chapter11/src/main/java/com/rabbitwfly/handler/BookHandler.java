package com.rabbitwfly.handler;

import com.rabbitmq.client.Channel;
import com.rabbitwfly.config.RabbitConfig;
import com.rabbitwfly.entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * BOOK_QUEUE 消费者
 */
@Component
public class BookHandler {

    private static final Logger log = LoggerFactory.getLogger(BookHandler.class);

    /**
     * <p>TODO 该方案是 spring-boot-data-amqp 默认的方式,不太推荐。具体推荐使用  listenerManualAck()</p>
     * 默认情况下,如果没有配置手动ACK, 那么Spring Data AMQP 会在消息消费完毕后自动帮我们去ACK
     * 存在问题：如果报错了,消息不会丢失,但是会无限循环消费,一直报错,如果开启了错误日志很容易就吧磁盘空间耗完
     * 解决方案：手动ACK,或者try-catch 然后在 catch 里面讲错误的消息转移到其它的系列中去
     * spring.rabbitmq.listener.simple.acknowledge-mode=manual
     * <p>
     *
     * @param book 监听的内容
     */
    @RabbitListener(queues = {RabbitConfig.DEFAULT_BOOK_QUEUE})
    public void listenerAutoAck(Book book, Message message, Channel channel){

        final long deliverTag = message.getMessageProperties().getDeliveryTag();
        try{
            log.info("[listenerAutoAck 监听的消息] - [{}]", book.toString());
            // TODO 通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(deliverTag, false);
        } catch (IOException e){
            try{
                //TODO 处理失败，重新压入MQ
                channel.basicRecover();
            } catch (IOException e1){
                e1.printStackTrace();
            }
        }
    }

    @RabbitListener(queues = {RabbitConfig.MANUAL_BOOK_QUEUE})
    public void listenerManualAck(Book book, Message message, Channel channel) {
        log.info("[listenerManualAck 监听的消息] - [{}]", book.toString());
        try {
            // TODO 通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            // TODO 如果报错了,那么我们可以进行容错处理,比如转移当前消息进入其它队列
        }
    }



}

