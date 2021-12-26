package com.distributed.roomconsumer.websocket;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.distributed.roomapi.model.Message;
import com.distributed.roomapi.model.Profile;
import com.distributed.roomapi.model.User;
import com.distributed.roomapi.service.MessageService;
import com.distributed.roomapi.service.ProfileService;
import com.distributed.roomapi.service.UserService;
import com.distributed.roomapi.service.WebSocketService;
import com.distributed.roomconsumer.config.websocketConfig.CustomSpringConfigurator;
import com.distributed.roomconsumer.util.jsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;

@ServerEndpoint(value = "/websocket/{userId}", configurator = CustomSpringConfigurator.class)
@Component
public class WebSocket {

    private static final Logger logger = LoggerFactory.getLogger(WebSocket.class);

    @Reference
    WebSocketService webSocketService;

    @Reference
    MessageService messageService;

    @Reference
    UserService userService;

    @Reference
    ProfileService profileService;

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
            }
            webSocketService.addUserAndSocket2Redis(userId, this);
            logger.info("Welcome to connect websocket, user: " + this.userId +
                    " current online num is: "+ webSocketService.getSumOfSocket());
            List<Message> list = messageService.selectUnReadMessage(this.userId);
            try {
                List<Map<String, Object>> unReadList = new ArrayList<>();
                for(Message message : list) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("message", message);
                    Integer fromId = message.getFromId();
                    Profile profile = profileService.getProfileByUserId(fromId);
                    map.put("headUrl", profile.getHeadUrl());
                    map.put("nickName", profile.getNickName());
                    unReadList.add(map);
                }
                String mes = jsonUtil.getJSONString(0, unReadList);
                logger.info(mes);
                String socketJsonString = webSocketService.getSocketByUserId(userId);
                WebSocket webSocket = JSONObject.parseObject(socketJsonString, WebSocket.class);
                webSocket.sendMessage(mes);
                messageService.clearUnReadMessage(this.userId);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("the message from client-" + userId + ": " + message);
        if(message == null || message.isBlank()) {
            logger.warn("message can't be empty");
            return ;
        }
        JSONObject jsonObject = JSON.parseObject(message);
        Message messageDto = JSONObject.toJavaObject(jsonObject, Message.class);
        Integer toId = messageDto.getToId();
        messageDto.setFromId(this.userId);
        messageDto.setIsRead(0);
        messageDto.setStatus(0);
        messageDto.setCreateDate(new Date());
        User toUser = userService.selectUserById(toId);
        User fromUser = userService.selectUserById(userId);
        if(toUser == null || toUser.getStatus() > 0 || fromUser == null || fromUser.getStatus() > 0) {
            logger.error("User exception");
            return ;
        }
        try {
            if(webSocketService.containsSocket(toId)) {
                getSocket(toId).sendMessage(message);
            } else {
                messageDto.setIsRead(1);
            }
            messageService.addMessage(messageDto);
        } catch (IOException e) {
            logger.error("Sendmessage error:" + e.getMessage());
        }
    }

    @OnClose
    public void onClose() {
        synchronized (obj) {
            if(webSocketService.containsSocket(userId)) {
                webSocketService.deleteUserInRedis(userId);
            }
            logger.info("one connection close currnet online num is: " + webSocketService.getSumOfSocket());
        }
    }


    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("Occur error!");
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    public WebSocket getSocket(Integer userId) {
        String socketJsonString = webSocketService.getSocketByUserId(userId);
        return JSONObject.parseObject(socketJsonString, WebSocket.class);
    }
}
