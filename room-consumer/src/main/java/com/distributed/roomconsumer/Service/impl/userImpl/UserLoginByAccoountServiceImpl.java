package com.distributed.roomconsumer.Service.impl.userImpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.distributed.roomapi.model.User;
import com.distributed.roomapi.service.UserResposity;
import com.distributed.roomconsumer.Service.factory.UserLoginAbstractService;
import com.distributed.roomconsumer.util.newProjectUtil;

import java.util.Objects;
import java.util.UUID;

@Service
@org.springframework.stereotype.Service
public class UserLoginByAccoountServiceImpl extends UserLoginAbstractService {

    @Reference
    UserResposity userResposity;

    @Override
    public User login(String account, String password) {
        if(account == null || account.isEmpty() || password == null || password.isEmpty()) {
            throw new NullPointerException();
        }
        User user = userResposity.selectUserByAccount(account);
        if(user == null) {
            throw new IllegalArgumentException("user is null");
        }
        if(!Objects.equals(newProjectUtil.MD5(password + user.getSalt()), user.getPassword())) {
            throw new IllegalArgumentException("password is wrong");
        }
        return user;
    }

    @Override
    public User register(String account, String password) {
        if(account == null || account.isEmpty() || password == null || password.isEmpty()) {
            throw new NullPointerException();
        }
        User user = new User();
        String salt = UUID.randomUUID().toString().substring(0, 6);
        user.setSalt(salt);
        user.setAccount(account);
        user.setPassword(newProjectUtil.MD5(password + salt));
        user.setStatus(0);
        userResposity.addUser(user);
        return user;
    }
}
