package com.study.MQ_Springboot.direct;

import com.rabbitmq.client.Channel;
import com.study.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j   // implements ChannelAwareMessageListener
public class HelloReceiver  {

    @RabbitHandler
    @RabbitListener(queues = "queue_direct")
    public void receive(Message message, Channel channel) throws IOException {
        try {
            //int i = 1/0;
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            System.out.println("接受到的消息为"+new String(message.getBody(),"UTF-8"));
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
        }
    }

    public void process(String hello) {
        int i = 1/0;

        log.info("Receiver  : " + hello);
        log.info("////////////////////////////////////////////////////////////////////\n" +
                "//                          _ooOoo_                               //\n" +
                "//                         o8888888o                              //\n" +
                "//                         88\" . \"88                              //\n" +
                "//                         (| ^_^ |)                              //\n" +
                "//                         O\\  =  /O                              //\n" +
                "//                      ____/`---'\\____                           //\n" +
                "//                    .'  \\\\|     |//  `.                         //\n" +
                "//                   /  \\\\|||  :  |||//  \\                        //\n" +
                "//                  /  _||||| -:- |||||-  \\                       //\n" +
                "//                  |   | \\\\\\  -  /// |   |                       //\n" +
                "//                  | \\_|  ''\\---/''  |   |                       //\n" +
                "//                  \\  .-\\__  `-`  ___/-. /                       //\n" +
                "//                ___`. .'  /--.--\\  `. . ___                     //\n" +
                "//              .\"\" '<  `.___\\_<|>_/___.'  >'\"\".                  //\n" +
                "//            | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |                 //\n" +
                "//            \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /                 //\n" +
                "//      ========`-.____`-.___\\_____/___.-`____.-'========         //\n" +
                "//                           `=---='                              //\n" +
                "//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //\n" +
                "//             佛祖保佑       永不宕机      永无BUG               //\n" +
                "////////////////////////////////////////////////////////////////////");
    }

    @RabbitHandler
    public void process111(User user) {
        System.out.println("Receiver  : " + user.toString());
        System.out.println("=============================================================================================================================================");
    }

    /*@Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            String messageStr = new String(message.getBody());
            log.info("===============================");

            //用于肯定确认
            channel.basicAck(deliveryTag, true);

        } catch (Exception e) {

            //用于否定确认
            channel.basicNack(deliveryTag,false,true);
//			channel.basicReject(deliveryTag, true);//为true会重新放回队列

            e.printStackTrace();
        }
    }*/
}
