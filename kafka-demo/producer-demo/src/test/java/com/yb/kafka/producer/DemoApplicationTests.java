package com.yb.kafka.producer;

import com.yb.kafka.producer.controller.KafkaProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Test
    public void kcontextLoads() {
        //发送消息
        kafkaProducer.send();
        System.err.println("发送成功");
    }

}
