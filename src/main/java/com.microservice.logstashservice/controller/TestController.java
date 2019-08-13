package com.microservice.logstashservice.controller;

import com.alibaba.fastjson.JSON;
import com.microservice.logstashservice.config.HystrixUtil;
import com.microservice.logstashservice.model.User;
import com.ning.http.client.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author hexudong
 * @description 被调用接口
 * @date 2019-08-07
 */
@Api("服务容错的相关api")
@RestController
@RequestMapping("/hystrix")
public class TestController {

    @Autowired
    private HystrixUtil hystrixUtil;

    @ApiOperation("根据用户名获取用户信息")
    @RequestMapping(value = "/getUserByName", method = RequestMethod.GET)
    public User getUserByName(@RequestParam("username") String username) {
        User user = null;
        String url = "http://localhost:8080/CommunicationD/getUser?username=" + username;

        Response response = hystrixUtil.execute(
                "CommunicationD", "getUser", url);
        if (response != null) {
            try {
                user = JSON.parseObject(response.getResponseBody(), User.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
}
