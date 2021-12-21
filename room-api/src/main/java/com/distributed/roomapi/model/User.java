package com.distributed.roomapi.model;

import lombok.Data;

import java.io.Serializable;

/**
 * login register logout
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 3190558630572515656L;

    /**
     * user id
     */
    private Integer id;

    /**
     * user account
     */
    private String account;

    /**
     * user password
     */
    private String password;

    /**
     * md5(password+salt) = db password
     */
    private String salt;

    /**
     * 0: normal
     * 1: abnormal
     * 2: ban
     */
    private String status;
}
