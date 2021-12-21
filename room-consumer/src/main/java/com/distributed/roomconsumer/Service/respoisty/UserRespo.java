package com.distributed.roomconsumer.Service.respoisty;

import com.distributed.roomapi.model.User;
import com.distributed.roomconsumer.responsebody.LoginSessionResponseBody;

public interface UserRespo {

    LoginSessionResponseBody login(String account, String password, Long expireTime);

    LoginSessionResponseBody register(String account, String password, Long expireTime);

}
