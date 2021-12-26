package com.distributed.roomapi.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Profile implements Serializable {

    private static final long serialVersionUID = 6372718512743690472L;

    private Integer id;

    private Integer createDate;

    private Integer userId;

    private String nickName;

    private String bio;

    private String headUrl;

    private String location;

    private String job;
}
