package com.microservice.logstashservice.config;

import com.netflix.hystrix.*;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hexudong
 * @description 集成服务降级容错功能的服务通信类
 * @date 2019-08-08
 */
@Component
public class HystrixUtil {

    @Autowired
    private AsyncHttpClient asyncHttpClient;

    public Response execute(String serviceName, String methodName, String url){
        HystrixCommand.Setter setter = HystrixCommand.Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey(serviceName));
        setter.andCommandKey(HystrixCommandKey.Factory.asKey(serviceName+":"+methodName));
        setter.andCommandPropertiesDefaults(
                HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(3000));
        setter.andThreadPoolPropertiesDefaults(
                HystrixThreadPoolProperties.Setter().withCoreSize(20));
        return new MyHystrixCommand(setter, url, asyncHttpClient).execute();


    }
}
