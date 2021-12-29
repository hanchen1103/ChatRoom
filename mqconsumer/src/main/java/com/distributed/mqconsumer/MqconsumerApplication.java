package com.distributed.mqconsumer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.distributed.roomapi.RoomApiApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableDubbo
public class MqconsumerApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MqconsumerApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MqconsumerApplication.class, args);
    }

}
