package com.distributed.roomconsumer.websocket;

import com.alibaba.dubbo.config.annotation.Reference;
import com.distributed.roomapi.model.Message;
import com.distributed.roomapi.model.User;
import com.distributed.roomapi.service.WebSocketService;
import com.distributed.roomconsumer.config.websocketConfig.CustomSpringConfigurator;
import com.distributed.roomconsumer.util.jsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint(value = "/websocket/{userId}", configurator = CustomSpringConfigurator.class)
@Component
public class WebSocket {

    private static final Logger logger = LoggerFactory.getLogger(WebSocket.class);

    @Reference
    WebSocketService webSocketService;

    private Integer userId;

    private Session session;

    private static final Object obj = new Object();

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") Integer userId) {
        if(userId == null) {
            return ;
        }
        synchronized (obj) {
            this.session = session;
            this.userId = userId;
            if(webSocketService.containsSocket(userId)) {
                webSocketService.deleteUserInRedis(userId);
                webSocketService.addUserAndSocket2Redis(userId, this);
            } else {
                webSocketService.addUserAndSocket2Redis(userId, this);
            }
            logger.info("Welcome to connect websocket, user: " + this.userId +
                    " current online num is: "+ webSocketService.getSumOfSocket());
            List<Message> list = messageService.selectUnRead(this.userId);
            try {
                List<Map<String, Object>> unReadList = new ArrayList<>();
                for(Message message : list) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("message", message);
                    Integer fromId = message.getFromId();
                    User user = userService.selectById(fromId);
                    map.put("head_url", user.getHead_url());
                    map.put("name", user.getAccount());
                    unReadList.add(map);
                }
                String mes = jsonUtil.getJSONString(0, unReadList);
                logger.info(mes);
                webSocketMap.get(this.userId).sendMessage(mes);
                messageService.clearUnRead(this.userId);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
