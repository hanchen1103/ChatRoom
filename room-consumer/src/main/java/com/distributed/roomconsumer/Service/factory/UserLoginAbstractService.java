package com.distributed.roomconsumer.Service.factory;

import com.distributed.roomapi.model.User;
import com.distributed.roomconsumer.Service.respoisty.UserRespo;

public abstract class UserLoginAbstractService implements UserRespo {

    @Override
    public User login(String account, String password) {
        return null;
    }

    @Override
    public User register(String account, String password) {
        return null;
    }

}
