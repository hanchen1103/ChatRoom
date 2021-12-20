package com.distributed.roomconsumer.Service.respoisty;

import com.distributed.roomconsumer.responsebody.chatProfileResponseBody;

import java.util.List;

/**
 * message services
 */
public interface MessageRespo {

    /**
     * get user's current chat profile
     * @param fromId source user id
     * @param toId to user id
     * @return list
     */
    List<chatProfileResponseBody> getFromIdAndToIdProfile(Integer fromId, Integer toId,
                                                          Integer limit, Integer offset);

    /**
     * send message
     * @param content string or url
     * @param fromId user from id
     * @param toId user to id
     * @return the sent message id
     */
    Integer sendMessage(String content, Integer type, Integer fromId, Integer toId, Integer isRead);


}
