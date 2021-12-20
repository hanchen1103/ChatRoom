package com.distributed.roomapi.service;

import com.distributed.roomapi.model.Message;

import java.util.List;

public interface MessageResposity {

    /**
     * add a new message to db
     * @return message's id
     */
    Integer addMessage(Message message);

    List<Message> selectByFromIdAndToId(Integer fromId, Integer toId, Integer limit, Integer offset);


}
