package com.distributed.roomconsumer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

//@SpringBootApplication(scanBasePackages = "com.distributed.roomconsumer.interceptor")
@EnableDubbo
@EnableWebSocket
@SpringBootApplication
public class RoomConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoomConsumerApplication.class, args);
    }

}
