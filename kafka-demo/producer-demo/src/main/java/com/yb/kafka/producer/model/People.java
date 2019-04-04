package com.yb.kafka.producer.model;

import java.io.Serializable;
import java.util.UUID;

/**
  * @Description: people实体类
  * @author yangbiao
  * @date 2018/11/6
 */
public class People implements Serializable {
    private static final long serialVersionUID = -5848075845062642645L;

    /**
     * id
     */
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 居住地
     */
    private String address;

    public People() {
        this.id = UUID.randomUUID().toString().replaceAll("-", "");
    }

    public People(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.id = UUID.randomUUID().toString().replaceAll("-", "");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}