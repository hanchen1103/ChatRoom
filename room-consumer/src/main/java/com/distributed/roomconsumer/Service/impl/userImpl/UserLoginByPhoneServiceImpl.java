package com.distributed.roomconsumer.Service.impl.userImpl;

import com.distributed.roomapi.model.User;
import com.distributed.roomconsumer.Service.respoisty.UserRespo;
import com.distributed.roomconsumer.responsebody.LoginSessionResponseBody;
import org.springframework.stereotype.Service;

@Service
@com.alibaba.dubbo.config.annotation.Service
public class UserLoginByPhoneServiceImpl implements UserRespo {


    @Override
    public LoginSessionResponseBody login(String account, String password, Long expireTime) {
        return null;
    }

    @Override
    public LoginSessionResponseBody register(String account, String password, Long expireTime) {
        return null;
    }
}
