package com.distributed.roomapi.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * user login, register, logout
 */
@Data
public class Message implements Serializable {

    private static final long serialVersionUID = 3848563999889745681L;

    /**
     * message id
     */
    private Integer id;

    /**
     * content, including file url or string
     */
    private String content;

    /**
     * message type including vedio, pic, text, audio (various files)
     */
    private Integer type;

    /**
     * createtime
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /**
     * source user id
     */
    private Integer fromId;

    /**
     * touser id
     */
    private Integer toId;

    /**
     * message status
     * 0: normal
     * 1: delete
     * 2: clean
     */
    private Integer status;

    /**
     * is online can read
     */
    private Integer isRead;

}
