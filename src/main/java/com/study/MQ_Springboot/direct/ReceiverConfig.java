package com.study.MQ_Springboot.direct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

//两种方式之一，另一种方式是在《消费者端》实现
//@Configuration
@Slf4j
public class ReceiverConfig {

    //@Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("queue_direct");              // 监听的队列
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);        // 手动确认
        container.setMessageListener(new ChannelAwareMessageListener() {

            @Override
            public void onMessage(Message message, com.rabbitmq.client.Channel channel) throws Exception {
                try {
                    log.info("消费端接收到消息:" + message.getMessageProperties() + ":" + new String(message.getBody()));
                    log.info("topic:"+message.getMessageProperties().getReceivedRoutingKey());
                    // deliveryTag是消息传送的次数，我这里是为了让消息队列的第一个消息到达的时候抛出异常，处理异常让消息重新回到队列，然后再次抛出异常，处理异常拒绝让消息重回队列
					/*if (message.getMessageProperties().getDeliveryTag() == 1
							|| message.getMessageProperties().getDeliveryTag() == 2) {
						throw new Exception();
					}*/

                    channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); // false只确认当前一个消息收到，true确认所有consumer获得的消息
                } catch (Exception e) {
                    e.printStackTrace();

                    if (message.getMessageProperties().getRedelivered()) {
                        System.out.println("消息已重复处理失败,拒绝再次接收...");
                        channel.basicReject(message.getMessageProperties().getDeliveryTag(), true); // 拒绝消息
                    } else {
                        System.out.println("消息即将再次返回队列处理...");
                        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true); // requeue为是否重新回到队列
                    }
                }
            }
        });
        /*container.setMessageListener((ChannelAwareMessageListener) (message, channel) -> {      //消息处理
            log.info("====接收到消息=====");
            log.info(new String(message.getBody()));

            if(message.getMessageProperties().getHeaders().get("error") == null){
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
                log.info("消息已经确认");
            }else {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
                //channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
                log.info("消息拒绝");
            }

        });*/
        return container;
    }
}

