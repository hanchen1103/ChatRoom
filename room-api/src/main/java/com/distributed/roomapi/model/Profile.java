package com.distributed.roomapi.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

@Data
@Document(indexName = "profile_index", createIndex = true)
public class Profile implements Serializable {

    private static final long serialVersionUID = 6372718512743690472L;

    @Id
    @Field(type = FieldType.Integer)
    private Integer id;

    @Field(type = FieldType.Integer)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Integer createDate;

    @Field(type = FieldType.Integer)
    private Integer userId;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String nickName;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String bio;

    @Field(type = FieldType.Text)
    private String headUrl;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String location;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String job;
}
