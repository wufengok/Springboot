package com.skonst.report.MQ_test.route;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.skonst.report.MQ_test.ConnectionUtil;

public class Sender_route {
    public  final static String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        // 消息内容
        String warningmessage = "warningmessage";
        String errormessage = "errormessage";
        String successmessage = "successmessage";
        channel.basicPublish(EXCHANGE_NAME, "warning", null, warningmessage.getBytes());
        channel.basicPublish(EXCHANGE_NAME, "error", null, errormessage.getBytes());
        channel.basicPublish(EXCHANGE_NAME, "success", null, successmessage.getBytes());
        System.out.println("====================发送成功====================");

        channel.close();
        connection.close();
    }
}
