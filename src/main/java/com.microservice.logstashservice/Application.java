package com.microservice.logstashservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 启动类
 * 访问路径： http://localhost:8082/swagger-ui.html
 */
@SpringBootApplication
@EnableSwagger2
public class Application implements EmbeddedServletContainerCustomizer {
    public static void main(String[] args) {
        SpringApplication sa = new SpringApplication(Application.class);
        sa.run(args);
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(8082);
    }
}
