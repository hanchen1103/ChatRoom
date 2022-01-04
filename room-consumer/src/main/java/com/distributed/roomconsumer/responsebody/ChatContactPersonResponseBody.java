package com.distributed.roomconsumer.responsebody;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * all contact list display
 */
@Data
public class ChatContactPersonResponseBody implements Serializable {

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
    @Field(type = FieldType.Date)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date sendDate;

    /**
     * message's id
     */
    private Integer messageId;
}
