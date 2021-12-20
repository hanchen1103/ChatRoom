package com.distributed.roomconsumer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class RoomConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoomConsumerApplication.class, args);
    }

}
