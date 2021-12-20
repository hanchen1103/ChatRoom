package com.distributed.roomconsumer.responsebody;

import com.distributed.roomapi.model.Message;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * between contact person's chat profile
 */
@Data
public class chatProfileResponseBody implements Serializable {

    private static final long serialVersionUID = 5114247454630301420L;

    /**
     * from user id
     */
    private Integer fromUserId;

    /**
     * to user id
     */
    private Integer toUserId;

    /**
     * from user's info
     */
    private String fromUserHeadUrl;

    /**
     * to user's info
     */
    private String toUserHeadUrl;

    /**
     * message list (offset nums)
     */
    private List<Message>  messageList;

    /**
     * nickName
     */
    private String fromUser2toUserNickName;

}
