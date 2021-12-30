package com.distributed.roomconsumer.Service.resposity;

import com.distributed.roomconsumer.responsebody.LoginSessionResponseBody;

public interface UserLoginResposity {

    LoginSessionResponseBody login(String account, String password, Long expireTime);

    LoginSessionResponseBody register(String account, String password, Long expireTime);

    void logout(Integer userId);
}
