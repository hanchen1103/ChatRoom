package com.distributed.roomconsumer.Service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.distributed.roomapi.model.Message;
import com.distributed.roomapi.service.MessageResposity;
import com.distributed.roomconsumer.Service.respoisty.MessageRespo;
import com.distributed.roomconsumer.responsebody.ChatProfileResponseBody;

import java.util.Date;
import java.util.List;

@Service
@org.springframework.stereotype.Service
public class MessageRespoImpl implements MessageRespo {

    @Reference
    MessageResposity messageResposity;

    @Override
    public List<ChatProfileResponseBody> getFromIdAndToIdProfile(Integer fromId, Integer toId,
                                                                 Integer limit, Integer offset) {
        if(fromId == null || toId == null || limit == null || offset == null) {
             throw new NullPointerException("param exception");
        }
        if(limit < 0 || offset <= 0) {
            throw new IllegalArgumentException("limit and offset exception");
        }
        List<Message> list = messageResposity.selectByFromIdAndToId(fromId, toId, limit, offset);
        return null;
    }

    @Override
    public Integer sendMessage(String content, Integer type, Integer fromId, Integer toId, Integer isRead) {
        if(fromId == null || toId == null || content == null || type == null) {
            throw new NullPointerException("param exception");
        }
        Message message = new Message();
        message.setType(type);
        message.setCreateDate(new Date());
        message.setToId(toId);
        message.setFromId(fromId);
        message.setCreateDate(new Date());
        message.setContent(content);
        message.setStatus(1);
        message.setIsRead(isRead);
        return messageResposity.addMessage(message);
    }
}
