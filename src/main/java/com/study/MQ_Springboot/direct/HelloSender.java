package com.study.MQ_Springboot.direct;

import com.study.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class HelloSender implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback  {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public String queue_exception() {
        rabbitTemplate.convertAndSend("exchange_name", "routing_Key", "哈哈哈哈哈哈");
        return "ok";
    }

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void queue_direct() {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//24小时制
        String context = "hello " + date;
        System.out.println("Sender : " + context);

        User user = new User();
        user.setId(1L);
        user.setNickName("大饼");

        //简单对列的情况下routingKey即为Q名
        //this.rabbitTemplate.convertAndSend("q_hello", user);
        this.amqpTemplate.convertAndSend("queue_direct", context);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("ConfirmCallback:     "+"相关数据："+correlationData);
        log.info("ConfirmCallback:     "+"确认情况："+ack);
        log.info("ConfirmCallback:     "+"原因："+cause);
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("ReturnCallback:     "+"消息："+message);
        log.info("ReturnCallback:     "+"回应码："+replyCode);
        log.info("ReturnCallback:     "+"回应信息："+replyText);
        log.info("ReturnCallback:     "+"交换机："+exchange);
        log.info("ReturnCallback:     "+"路由键："+routingKey);
    }
}
