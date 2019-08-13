package com.microservice.logstashservice.config;

import com.netflix.hystrix.HystrixCommand;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.RequestBuilder;
import com.ning.http.client.Response;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author hexudong
 * @description 实现服务降级熔断功能
 * @date 2019-08-08
 */
public class MyHystrixCommand extends HystrixCommand<Response> {

    private String url;
    private AsyncHttpClient asyncHttpClient;

    protected MyHystrixCommand(Setter setter, String url, AsyncHttpClient asyncHttpClient) {
        super(setter);
        this.url = url;
        this.asyncHttpClient = asyncHttpClient;
    }

    @Override
    protected Response run() throws Exception {
        Response response = null;
        com.ning.http.client.Request request =
                new RequestBuilder().setUrl(url).build();
        Future<Response> responseFuture =
                asyncHttpClient.executeRequest(request);
        try{
            response = responseFuture.get();
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response getFallback(){
        throw new RuntimeException("===========");
    }
}
