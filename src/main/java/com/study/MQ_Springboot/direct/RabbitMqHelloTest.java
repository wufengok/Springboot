package com.study.MQ_Springboot.direct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqHelloTest {

    @Autowired
    private HelloSender helloSender;

    @Test
    public void queue_direct() {
        helloSender.queue_direct();
    }

    @Test
    public void queue_exception() {
        helloSender.queue_exception();
    }
}
