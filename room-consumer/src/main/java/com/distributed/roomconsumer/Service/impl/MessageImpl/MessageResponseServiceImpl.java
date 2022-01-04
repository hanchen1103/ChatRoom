package com.distributed.roomconsumer.Service.impl.MessageImpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.distributed.roomapi.model.Message;
import com.distributed.roomapi.model.Profile;
import com.distributed.roomapi.service.MessageEsService;
import com.distributed.roomapi.service.MessageService;
import com.distributed.roomconsumer.Service.resposity.EsQueryResposity;
import com.distributed.roomconsumer.Service.resposity.MessageResposity;
import com.distributed.roomconsumer.Service.resposity.ProfileRespoisty;
import com.distributed.roomconsumer.config.MQConfig.KafkaProducer;
import com.distributed.roomconsumer.responsebody.ChatContactPersonResponseBody;
import com.distributed.roomconsumer.responsebody.ChatProfileResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@org.springframework.stereotype.Service
public class MessageResponseServiceImpl implements MessageResposity, EsQueryResposity {

    @Reference
    MessageService messageResposity;

    @Reference
    MessageEsService messageEsService;

    @Autowired
    KafkaProducer kafkaProducer;

    @Autowired
    ProfileRespoisty profileRespoisty;


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
        kafkaProducer.addEsMessageTopic(message);
        return messageResposity.addMessage(message);
    }

    @Override
    public List<ChatContactPersonResponseBody> selectContactByFromIdAnd2Id(Integer userId, Integer limit, Integer offset) {
        List<Message> list = messageResposity.selectContactByFromIdAnd2Id(userId, limit, offset);
        List<ChatContactPersonResponseBody> res = new ArrayList<>();
        for(Message i : list) {
            ChatContactPersonResponseBody chatContactPersonResponseBody = new ChatContactPersonResponseBody();
            Integer targetId = Objects.equals(i.getFromId(), userId) ? i.getToId() : i.getFromId();
            Profile profile = profileRespoisty.selectByUserId(targetId);
            chatContactPersonResponseBody.setContent(i.getContent());
            chatContactPersonResponseBody.setSendDate(i.getCreateDate());
            chatContactPersonResponseBody.setToUserHeadUrl(profile.getHeadUrl());
            chatContactPersonResponseBody.setToUserNickName(profile.getNickName());
            chatContactPersonResponseBody.setToUserId(targetId);
            chatContactPersonResponseBody.setMessageId(i.getId());
            res.add(chatContactPersonResponseBody);
        }
        return res;
    }

    @Override
    public List<Message> selectUnReadMessage(Integer userId) {
        return messageResposity.selectUnReadMessage(userId);
    }

    @Override
    public void clearUnReadMessage(Integer userId) {
        messageResposity.clearUnReadMessage(userId);
    }

    @Override
    public List<Message> queryMessage(Integer fromId, Integer toId, String q) {
        if(fromId == null || toId == null || q == null || q.isEmpty()) {
            throw new NullPointerException();
        }

        if(fromId <= 0 || toId <= 0) {
            throw new IllegalArgumentException("id exception");
        }
        return messageEsService.queryMessageByFromIdAnd2IdAndContent(fromId, toId, q);
    }
}
