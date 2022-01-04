package com.distributed.roomapi.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * user login, register, logout
 */
@Data
@Document(indexName = "message_index", createIndex = true)
public class Message implements Serializable {

    private static final long serialVersionUID = 3848563999889745681L;

    /**
     * message id
     */
    @Id
    private int id;

    /**
     * content, including file url or string
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String content;

    /**
     * message type including vedio, pic, text, audio (various files)
     */
    @Field(type = FieldType.Integer)
    private Integer type;

    /**
     * createtime
     */
    @Field(type = FieldType.Date)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /**
     * source user id
     */
    @Field(type = FieldType.Integer)
    private Integer fromId;

    /**
     * touser id
     */
    @Field(type = FieldType.Integer)
    private Integer toId;

    /**
     * message status
     * 0: normal
     * 1: delete
     * 2: clean
     */
    @Field(type = FieldType.Integer)
    private Integer status;

    /**
     * is online can read
     */
    @Field(type = FieldType.Integer)
    private Integer isRead;

}
