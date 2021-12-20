package com.distributed.roomconsumer.responsebody;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * all contact list display
 */
@Data
public class chatContactPersonResponseBody implements Serializable {

    private static final long serialVersionUID = -7545697779091426073L;

    /**
     * contact person id
     */
    private Integer toUserId;

    /**
     * contact person headurl
     */
    private String toUserHeadUrl;

    /**
     * contact person nickname
     */
    private String toUserNickName;

    /**
     * lasttime history message
     */
    private String content;

    /**
     * lasttime message's createtime
     */
    private Date sendDate;
}
