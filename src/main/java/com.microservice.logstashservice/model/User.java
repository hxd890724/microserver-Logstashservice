package com.microservice.logstashservice.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hexudong
 * @description 模型类
 * @date 2019-08-07
 */
@Getter
@Setter
public class User {
    private String username;
    private int age;
}
