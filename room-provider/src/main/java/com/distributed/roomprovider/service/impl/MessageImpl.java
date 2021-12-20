package com.distributed.roomprovider.service.impl;

import com.distributed.roomapi.model.Message;
import com.distributed.roomapi.service.MessageResposity;
import com.distributed.roomprovider.mapper.MessageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@com.alibaba.dubbo.config.annotation.Service
public class MessageImpl implements MessageResposity {

    @Autowired
    private MessageDAO messageDAO;

    @Override
    public Integer addMessage(Message message) {
        if(message == null) {
            throw new NullPointerException("message param exception");
        }
        messageDAO.addMessage(message);
        return message.getId();
    }

    @Override
    public List<Message> selectByFromIdAndToId(Integer fromId, Integer toId, Integer limit, Integer offset) {
        if(fromId == null || toId == null || limit == null || offset == null) {
            throw new NullPointerException("param exception");
        }
        if(limit < 0 || offset <= 0) {
            throw new IllegalArgumentException("limit or offset error");
        }
        return messageDAO.selectMessageListByFromIdAndtoId(fromId, toId, limit, offset);
    }
}
