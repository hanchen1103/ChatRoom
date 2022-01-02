package com.distributed.roomconsumer.Service.impl.MessageImpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.distributed.roomapi.model.Message;
import com.distributed.roomapi.service.MessageService;
import com.distributed.roomconsumer.Service.resposity.MessageResposity;
import com.distributed.roomconsumer.responsebody.ChatContactPersonResponseBody;
import com.distributed.roomconsumer.responsebody.ChatProfileResponseBody;

import java.util.Date;
import java.util.List;

@Service
@org.springframework.stereotype.Service
public class MessageResponseServiceImpl implements MessageResposity {

    @Reference
    MessageService messageResposity;

    @Override
    public ChatProfileResponseBody getFromIdAnd2IdProfile(Integer fromId, Integer toId,
                                                                 Integer limit, Integer offset) {
        if(fromId == null || toId == null || limit == null || offset == null) {
             throw new NullPointerException("param exception");
        }
        if(limit < 0 || offset <= 0) {
            throw new IllegalArgumentException("limit and offset exception");
        }
        List<Message> list = messageResposity.selectByFromIdAndToId(fromId, toId, limit, offset);
        ChatProfileResponseBody chatProfileResponseBody = new ChatProfileResponseBody();
        chatProfileResponseBody.setMessageList(list);
        chatProfileResponseBody.setFromUserId(fromId);
        chatProfileResponseBody.setToUserId(toId);
        return chatProfileResponseBody;
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

    @Override
    public List<ChatContactPersonResponseBody> selectContactByFromIdAnd2Id(Integer userId) {
        return null;
    }

    @Override
    public List<Message> selectUnReadMessage(Integer userId) {
        return messageResposity.selectUnReadMessage(userId);
    }

    @Override
    public void clearUnReadMessage(Integer userId) {
        messageResposity.clearUnReadMessage(userId);
    }
}
