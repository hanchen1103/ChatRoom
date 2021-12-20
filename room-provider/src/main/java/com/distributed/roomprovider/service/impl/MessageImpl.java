package com.distributed.roomprovider.service.impl;

import com.distributed.roomapi.model.Message;
import com.distributed.roomapi.service.MessageResposity;
import com.distributed.roomprovider.mapper.MessageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
}
