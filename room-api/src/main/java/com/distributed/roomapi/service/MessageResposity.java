package com.distributed.roomapi.service;

import com.distributed.roomapi.model.Message;

public interface MessageResposity {

    /**
     * add a new message to db
     * @return message's id
     */
    Integer addMessage(Message message);


}
