package com.distributed.roomapi.service;

import com.distributed.roomapi.model.Message;

import java.util.List;

public interface MessageEsService {

    void save2Es(Message message);

    List<Message> queryMessageByFromIdAnd2IdAndContent(Integer fromId, Integer toId, String content);
}
