package com.distributed.roomconsumer.Service.resposity;

import com.distributed.roomapi.model.Message;
import com.distributed.roomconsumer.responsebody.ChatContactPersonResponseBody;
import com.distributed.roomconsumer.responsebody.ChatProfileResponseBody;

import java.util.List;

/**
 * message services
 */
public interface MessageResposity {

    /**
     * get user's current chat profile
     * @param fromId source user id
     * @param toId to user id
     * @return list
     */
    ChatProfileResponseBody getFromIdAnd2IdProfile(Integer fromId, Integer toId,
                                                          Integer limit, Integer offset);

    /**
     * send message
     * @param content string or url
     * @param fromId user from id
     * @param toId user to id
     * @return the sent message id
     */
    Integer sendMessage(String content, Integer type, Integer fromId, Integer toId, Integer isRead);


    List<ChatContactPersonResponseBody> selectContactByFromIdAnd2Id(Integer userId, Integer limit, Integer offset);

    List<Message> selectUnReadMessage(Integer userId);

    void clearUnReadMessage(Integer userId);
}
