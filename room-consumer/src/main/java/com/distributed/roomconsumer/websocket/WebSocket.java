package com.distributed.roomconsumer.websocket;

import com.distributed.roomconsumer.config.websocketConfig.CustomSpringConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint(value = "/websocket/{userId}", configurator = CustomSpringConfigurator.class)
@Component
public class WebSocket {

    private static AtomicInteger onlineCount = new AtomicInteger(0);

    private static final Logger logger = LoggerFactory.getLogger(WebSocket.class);

    private Integer userId;

    private Session session;

    private static final Object obj = new Object();

    

}
