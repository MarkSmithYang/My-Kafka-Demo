package com.yb.kafka.controller;

import com.yb.kafka.message.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangbiao
 * @Description:
 * @date 2018/11/7
 */
@RestController
@CrossOrigin
public class KafkaDemoController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @GetMapping("send")
    public String sendMessage(){
        kafkaProducer.send();
        return "success";
    }
}
