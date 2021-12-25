package com.distributed.roomapi.service;


public interface WebSocketService {

    Boolean containsSocket(Integer userId);

    void addUserAndSocket2Redis(Integer userId, Object object);

    void deleteUserInRedis(Integer userId);

    String getSocketByUserId(Integer userId);

    Long getSumOfSocket();

}
