package com.distributed.roomconsumer.Service.factory;

import com.distributed.roomapi.model.User;
import com.distributed.roomconsumer.Service.respoisty.UserRespo;
import com.distributed.roomconsumer.responsebody.LoginSessionResponseBody;


public abstract class UserLoginAbstractService implements UserRespo {


    @Override
    public LoginSessionResponseBody login(String account, String password, Long expireTime) {
        return null;
    }

    @Override
    public LoginSessionResponseBody register(String account, String password, Long expireTime) {
        return null;
    }


}
