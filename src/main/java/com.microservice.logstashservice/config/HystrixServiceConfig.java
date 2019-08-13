package com.microservice.logstashservice.config;

import com.netflix.hystrix.HystrixMetrics;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClientConfig;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hexudong
 * @description 单例Bean
 * @date 2019-08-08
 */
@Configuration
public class HystrixServiceConfig {

    @Bean
    public AsyncHttpClient asyncHttpClient() {
        AsyncHttpClientConfig.Builder builder =
                new AsyncHttpClientConfig.Builder()
                        .setRequestTimeout(50000)
                        .setConnectTimeout(20000)
                        .setReadTimeout(30000);
        return new AsyncHttpClient(builder.build());
    }

    @Bean(name = "hystrixRegistrationBean")
    public ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean registration = new ServletRegistrationBean(
                new HystrixMetricsStreamServlet(), "/hystrix.stream");
        registration.setName("hystrixServlet");
        registration.setLoadOnStartup(1);
        return registration;
    }

}
