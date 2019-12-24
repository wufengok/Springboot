package com.skonst.report.MQ_test.Work;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.skonst.report.MQ_test.ConnectionUtil;

import java.util.HashMap;

public class Sender_work {
    private final static String QUEUE_NAME = "limitTest";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //创建队列
        HashMap<String, Object> map = new HashMap<>();
        //设置队列最大的条数 10条
        map.put("x-max-length",100);
        //设置队列溢出方式    保留前10条
        map.put("x-overflow","reject-publish");
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, map);

        for (int i = 0; i < 50; i++) {
            // 消息内容
            String message = "" + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

        }
        AMQP.Queue.DeclareOk declareOk = channel.queueDeclarePassive(QUEUE_NAME);
        //获取队列中的消息个数
        int num = declareOk.getMessageCount();
        System.out.println(num);
        System.out.println("=================发送成功=================");
        channel.close();
        connection.close();
    }
}
