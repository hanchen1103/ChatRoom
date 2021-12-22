package com.distributed.roomconsumer.Service.impl.userImpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.distributed.roomapi.model.User;
import com.distributed.roomapi.service.SessionService;
import com.distributed.roomapi.service.UserResposity;
import com.distributed.roomconsumer.Service.respoisty.UserRespo;
import com.distributed.roomconsumer.responsebody.LoginSessionResponseBody;
import com.distributed.roomconsumer.util.newProjectUtil;

import java.util.Objects;
import java.util.UUID;

@Service
@org.springframework.stereotype.Service
public class UserLoginByAccoountServiceImpl implements UserRespo {

    @Reference
    UserResposity userResposity;

    @Reference
    SessionService sessionService;

    @Override
    public LoginSessionResponseBody login(String account, String password, Long expireTime) {
        if(account == null || account.isEmpty() || password == null || password.isEmpty() || expireTime == null) {
            throw new NullPointerException();
        }
        User user = userResposity.selectUserByAccount(account);
        if(user == null) {
            throw new IllegalArgumentException("user is null");
        }
        if(!Objects.equals(newProjectUtil.MD5(password + user.getSalt()), user.getPassword())) {
            throw new IllegalArgumentException("password is wrong");
        }
        String res = sessionService.addSession2Redis(user.getId(), expireTime);
        LoginSessionResponseBody loginSessionResponseBody = new LoginSessionResponseBody();
        loginSessionResponseBody.setUser(user);
        loginSessionResponseBody.setToken(res);
        return loginSessionResponseBody;
    }

    @Override
    public LoginSessionResponseBody register(String account, String password, Long expireTime) {
        if(account == null || account.isEmpty() || password == null || password.isEmpty() || expireTime == null) {
            throw new NullPointerException();
        }
        User user = new User();
        if(userResposity.selectUserByAccount(account) != null) {
            throw new IllegalArgumentException("user account is exist");
        }
        String salt = UUID.randomUUID().toString().substring(0, 6);
        user.setSalt(salt);
        user.setAccount(account);
        user.setPassword(newProjectUtil.MD5(password + salt));
        user.setStatus(0);
        userResposity.addUser(user);
        sessionService.addSession2Redis(user.getId(), expireTime);
        String res = sessionService.addSession2Redis(user.getId(), expireTime);
        LoginSessionResponseBody loginSessionResponseBody = new LoginSessionResponseBody();
        loginSessionResponseBody.setUser(user);
        loginSessionResponseBody.setToken(res);
        return loginSessionResponseBody;
    }

    @Override
    public void logout(Integer userId) {
        sessionService.deleteSessionFromRedis(userId);
    }
}
