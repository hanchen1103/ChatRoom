package com.distributed.roomconsumer.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.distributed.roomapi.model.Message;
import com.distributed.roomapi.model.Profile;
import com.distributed.roomapi.model.User;
import com.distributed.roomapi.service.ProfileService;
import com.distributed.roomconsumer.Service.impl.WebSocketImpl.ConWebSocketService;
import com.distributed.roomconsumer.Service.impl.userImpl.UserQueryServiceImpl;
import com.distributed.roomconsumer.Service.resposity.MessageResposity;
import com.distributed.roomconsumer.Service.resposity.ProfileRespoisty;
import com.distributed.roomconsumer.Service.resposity.UserLoginResposity;
import com.distributed.roomconsumer.Service.resposity.UserQueryResposity;
import com.distributed.roomconsumer.config.MQConfig.KafkaProducer;
import com.distributed.roomconsumer.config.websocketConfig.CustomSpringConfigurator;
import com.distributed.roomconsumer.util.jsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

@ServerEndpoint(value = "/websocket/{userId}", configurator = CustomSpringConfigurator.class)
@Component
public class WebSocket implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(WebSocket.class);

    private static final long serialVersionUID = -6176509130841513788L;

    @Autowired
    ConWebSocketService webSocketService;

    @Autowired
    MessageResposity messageResposity;

    @Autowired
    UserQueryServiceImpl userQueryService;

    @Autowired
    ProfileRespoisty profileRespoisty;

    @Autowired
    KafkaProducer kafkaProducer;

    private Integer userId;

    private Session session;

    private static final Object obj = new Object();

    private static HashMap<Integer, WebSocket> webSocketMap = new HashMap<>();

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
                webSocketMap.remove(userId);
            }
            webSocketMap.put(userId, this);
            webSocketService.addUserAndSocket2Redis(userId);
            logger.info("Welcome to connect websocket, user: " + this.userId +
                    " current online num is: "+ webSocketService.getSumOfSocket());
            List<Message> list = messageResposity.selectUnReadMessage(this.userId);
            try {
                List<Map<String, Object>> unReadList = new ArrayList<>();
                for(Message message : list) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("message", message);
                    Integer fromId = message.getFromId();
                    Profile profile = profileRespoisty.selectByUserId(fromId);
                    if(profile == null) {
                        continue;
                    }
                    map.put("headUrl", profile.getHeadUrl());
                    map.put("nickName", profile.getNickName());
                    unReadList.add(map);
                }
                String mes = jsonUtil.getJSONString(0, unReadList);
                logger.info(mes);
                if(mes != null) {
                    webSocketMap.get(this.userId).sendMessage(mes);
                }
                kafkaProducer.clearUnReadMessageTopic(this.userId);
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
        messageDto.setType(1);
        messageDto.setCreateDate(new Date());
        User toUser = userQueryService.selectUserById(toId);
        User fromUser = userQueryService.selectUserById(userId);
        if(toUser == null || toUser.getStatus() > 0 || fromUser == null || fromUser.getStatus() > 0) {
            logger.error("User exception");
            return ;
        }
        try {
            if(webSocketService.containsSocket(toId)) {
                webSocketMap.get(toId).sendMessage(message);
            } else {
                messageDto.setIsRead(1);
            }
            kafkaProducer.sendMessageTopic(messageDto);
            kafkaProducer.addEsMessageTopic(messageDto);
        } catch (IOException e) {
            logger.error("Sendmessage error:" + e.getMessage());
        }
    }

    @OnClose
    public void onClose() {
        synchronized (obj) {
            webSocketService.deleteUserInRedis(userId);
            webSocketMap.remove(userId);
        }
        logger.info("one connection close currnet online num is: " + webSocketService.getSumOfSocket());

    }


    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("Occur error!");
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

}
