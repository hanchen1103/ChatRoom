package com.distributed.roomconsumer.responsebody;

import com.distributed.roomapi.model.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginSessionResponseBody implements Serializable {

    private static final long serialVersionUID = -6531633422406336717L;

    private String token;

    private User user;
}
