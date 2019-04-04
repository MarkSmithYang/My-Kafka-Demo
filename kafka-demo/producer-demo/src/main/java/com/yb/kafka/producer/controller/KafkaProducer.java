package com.yb.kafka.producer.controller;

import com.alibaba.fastjson.JSONObject;
import com.yb.kafka.producer.model.People;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import java.util.UUID;

/**
  * @Description: 生产者--使用@EnableScheduling注解开启定时任务
  * @author yangbiao
  * @date 2018/11/7
 */
@Component
public class KafkaProducer {
 public static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    private int anInt=0;

    /**
     * 定时任务
     */
    public void send(){
        //通过Json传递过去好解析成对应的对象使用
        String s = JSONObject.toJSONString(new People("jack"+anInt, 19+anInt, "北京"));
        anInt++;
        String message = UUID.randomUUID().toString();
        ListenableFuture future = kafkaTemplate.send("MeTest2", message);
        kafkaTemplate.send("Me1","K1" ,s).
                addCallback(a->log.info("消息发送成功--"+a),b->log.info("消息发送失败=="+b));
        kafkaTemplate.send("MeTest1","K11" ,s).
                addCallback(a->log.info("消息发送成功6666--"+a),b->log.info("消息发送失败5555=="+b));
    }

}