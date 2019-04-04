package com.yb.kafka.message.consumer;

import com.alibaba.fastjson.JSONObject;
import com.yb.kafka.model.People;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author yangbiao
 * @Description: 消费者---使用@KafkaListener注解,可以指定:主题,分区,消费组
 * @date 2018/11/6
 */
@Component
public class KafkaConsumer {
    public static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 这个可以获取到除了消息的其他东西--->上面消费完了,下面就没得消费了,因为主题是一样对的
     *
     * @param record
     */
    @KafkaListener(topics = {"People"})
    @Transactional(rollbackFor = Exception.class)
    public void consumerMessage(ConsumerRecord<String, String> record) {
        Optional<ConsumerRecord<String, String>> optional = Optional.ofNullable(record);
        //获取消息处理业务--->这里其实只需要消息即可,所以可以直接用String来接口即可
        // 不必用ConsumerRecord<String, String>来获取其他的信息
        if (optional.isPresent()) {
            //解析对象信息
            People people = JSONObject.parseObject(record.value(), People.class);
            //存储对象信息
            if (people != null) {
                try {
                    jdbcTemplate.update("insert into people (id,`name`,age,address) values (?,?,?,?)",
                            new Object[]{people.getId(), people.getName(), people.getAge(), people.getAddress()});
                    log.info("保存people成功");
                } catch (DataAccessException e) {
                    //如果异常则重试一次
                    jdbcTemplate.update("insert into people (id,`name`,age,address) values (?,?,?,?)",
                            new Object[]{people.getId(), people.getName(), people.getAge(), people.getAddress()});
                    log.info("重试保存people成功");
                }
            }
        } else {
            log.info("监听获取到的消息为空");
        }
    }

    /**
     * 这个简单点,只能获取到消息值
     *
     * @param message
     */
    @KafkaListener(topics = {"MeTest22"})
    public void receive(String message) {
        System.err.println("app_log--消费消息:" + message);
    }
}