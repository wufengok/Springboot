package com.skonst.report.MQ_test;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class ConsumerUtil {
    public static DefaultConsumer getConsumer(Channel channel) {

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            /**
             * consumerTag 消费者标签，在channel.basicConsume时候可以指定
             * envelope 消息包的内容，可从中获取消息id，路由key，交换机等信息
             * properties 消息属性信息
             * body 消息内容
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                //消息id
                System.out.println("消息id:  " + envelope.getDeliveryTag());
                //交换机
                System.out.println("交换机:  " + envelope.getExchange());
                //路由key
                System.out.println("路由key: " + envelope.getRoutingKey());
                //接受到的消息
                System.out.println("收到的消息:  " + new String(body, "utf-8"));

                System.out.println("---------------------------------");

            }
        };

        return consumer;
    }
}
