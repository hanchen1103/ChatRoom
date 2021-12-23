package com.distributed.roomapi.service;

import java.net.Socket;

public interface WebSocketService {

    Boolean containsSocket(Integer userId);

    void addUserAndSocket2Redis(Integer userId, Socket socket);
}
