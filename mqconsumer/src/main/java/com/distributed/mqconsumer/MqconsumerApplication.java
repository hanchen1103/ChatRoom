package com.distributed.mqconsumer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class MqconsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqconsumerApplication.class, args);
    }

}
