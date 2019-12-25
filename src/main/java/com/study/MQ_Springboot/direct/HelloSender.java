package com.study.MQ_Springboot.direct;

import com.study.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class HelloSender {

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
}
