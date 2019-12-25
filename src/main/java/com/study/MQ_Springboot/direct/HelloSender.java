package com.study.MQ_Springboot.direct;

import com.study.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class HelloSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public String sendException() {
        String msg = "rabbitmq生成者发送失败和消费失败处理方案";
        try {
            // 针对网络原因导致连接断开，利用retryTemplate重连10次
            RetryTemplate retryTemplate = new RetryTemplate();
            retryTemplate.setRetryPolicy(new SimpleRetryPolicy(10));
            rabbitTemplate.setRetryTemplate(retryTemplate);

            // 确认是否发到交换机，若没有则存缓存，用另外的线程重发，直接在里面用rabbitTemplate重发会抛出循环依赖错误
            rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
                if (!ack) {
                    // 存缓存操作
                    log.info(msg + "--发送失败:" + cause);
                }else {
                    log.info(msg + "--发送成功:" + cause);
                }
            });

            // 确认是否发到队列，若没有则存缓存，然后检查exchange, routingKey配置，之后重发
            rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
                // 存缓存操作
                log.info(new String(message.getBody()) + "找不到队列，exchange为" + exchange + ",routingKey为" + routingKey);
            });

            rabbitTemplate.convertAndSend("myExchange1", "routingKey4", msg);
        } catch (AmqpException e) {
            // 存缓存操作
            log.info(msg + "发送失败:原因重连10次都没连上。");
        }

        return "success";
    }

    public void send() {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//24小时制
        String context = "hello " + date;
        System.out.println("Sender : " + context);

        User user = new User();
        user.setId(1L);
        user.setNickName("大饼");

        //简单对列的情况下routingKey即为Q名
        //this.rabbitTemplate.convertAndSend("q_hello", user);
        this.amqpTemplate.convertAndSend("q_hello", context);
    }
}
