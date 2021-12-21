package com.distributed.roomconsumer.Service.impl.userImpl;

import com.distributed.roomapi.model.User;
import com.distributed.roomconsumer.Service.respoisty.UserRespo;
import org.springframework.stereotype.Service;

@Service
@com.alibaba.dubbo.config.annotation.Service
public class UserLoginByPhoneServiceImpl implements UserRespo {

    @Override
    public User login(String account, String password) {
        return null;
    }

    @Override
    public User register(String account, String password) {
        return null;
    }
}
