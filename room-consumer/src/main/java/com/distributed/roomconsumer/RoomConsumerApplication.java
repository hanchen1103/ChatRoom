package com.distributed.roomconsumer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.distributed.roomapi.RoomApiApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

//@SpringBootApplication(scanBasePackages = "com.distributed.roomconsumer.interceptor")
@EnableDubbo
@EnableWebSocket
@SpringBootApplication
public class RoomConsumerApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(RoomConsumerApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(RoomConsumerApplication.class, args);
    }

}
