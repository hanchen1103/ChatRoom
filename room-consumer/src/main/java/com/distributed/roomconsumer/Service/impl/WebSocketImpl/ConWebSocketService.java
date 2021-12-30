package com.distributed.roomconsumer.Service.impl.WebSocketImpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.distributed.roomapi.service.WebSocketService;
import org.springframework.stereotype.Service;

@Service
@com.alibaba.dubbo.config.annotation.Service
public class ConWebSocketService {

    @Reference
    WebSocketService webSocketService;

    public Boolean containsSocket(Integer userId) {
        return webSocketService.containsSocket(userId);
    }

    public void addUserAndSocket2Redis(Integer userId, Object object){
        webSocketService.addUserAndSocket2Redis(userId, object);
    }

    public void deleteUserInRedis(Integer userId) {
        webSocketService.deleteUserInRedis(userId);
    }

    public String getSocketByUserId(Integer userId) {
        return webSocketService.getSocketByUserId(userId);
    }

    public Long getSumOfSocket() {
        return webSocketService.getSumOfSocket();
    }
}
