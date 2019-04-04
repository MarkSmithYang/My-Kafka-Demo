package com.yb.kafka.consumer.listener;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
  * @Description: 消费者---使用@KafkaListener注解,可以指定:主题,分区,消费组
  * @author yangbiao
  * @date 2018/11/6
 */
@Component
public class KafkaConsumer {
 public static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    /**
     * 这个简单点,只能获取到消息值
     * @param message
     */
    @KafkaListener(topics = {"MeTest2"})
    public void receive(String message){
        System.out.println("app_log--消费消息:" + message);
        if(StringUtils.isNotBlank(message)){

        }
    }

    /**
     * 这个可以获取到除了消息的其他东西--->上面消费完了,下面就没得消费了,因为主题是一样对的
     * @param record
     */
    @KafkaListener(topics = {"Me1"},groupId = "test")
    public void consumerMessage(ConsumerRecord<String, String> record) {
        Optional<ConsumerRecord<String, String>> optional = Optional.ofNullable(record);
        if(optional.isPresent()){
            log.info("消费key:" + record.key());
            log.info("消费value:" + record.value());
            JSONObject jsonObject = JSONObject.parseObject(record.value());
            System.err.println(jsonObject);
        }else {
            log.info("null");
        }
    }
}