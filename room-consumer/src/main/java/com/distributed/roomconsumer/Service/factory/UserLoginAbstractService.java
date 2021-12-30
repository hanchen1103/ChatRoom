package com.distributed.roomconsumer.Service.factory;

import com.distributed.roomconsumer.Service.resposity.UserLoginResposity;
import com.distributed.roomconsumer.responsebody.LoginSessionResponseBody;


public abstract class UserLoginAbstractService implements UserLoginResposity {


    @Override
    public LoginSessionResponseBody login(String account, String password, Long expireTime) {
        return null;
    }

    @Override
    public LoginSessionResponseBody register(String account, String password, Long expireTime) {
        return null;
    }


}
