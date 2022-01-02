package com.distributed.roomconsumer.Service.resposity;

public interface WebSocketResposity {

    Boolean containsSocket(Integer userId);

    void addUserAndSocket2Redis(Integer userId);

    void deleteUserInRedis(Integer userId);

    String getSocketByUserId(Integer userId);

    Long getSumOfSocket();
}
