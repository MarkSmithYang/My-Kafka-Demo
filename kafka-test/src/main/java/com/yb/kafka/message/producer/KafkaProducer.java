package com.yb.kafka.message.producer;

import com.alibaba.fastjson.JSONObject;
import com.yb.kafka.model.People;
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
 * @author yangbiao
 * @Description: 生产者--使用@EnableScheduling注解开启定时任务
 * @date 2018/11/7
 */
@Component
public class KafkaProducer {
    public static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 定时任务
     */
    public void send() {
        //通过Json传递过去好解析成对应的对象使用
        String s = JSONObject.toJSONString(new People("jack", 19, "北京"));

        //发送消息给主题Me1
        kafkaTemplate.send("People", "PeopleKey", s).
                addCallback(a -> log.info("消息发送成功topic是--People--" + a), b -> log.info("消息发送失败topic是==People==" + b));
        //发送消息给主题MeTest2
        kafkaTemplate.send("MeTest22", "100").addCallback(o -> System.out.println("send-消息发送MeTest22成功：" + 100),
                throwable -> System.out.println("消息发送MeTest22失败：" + 100));
    }

}