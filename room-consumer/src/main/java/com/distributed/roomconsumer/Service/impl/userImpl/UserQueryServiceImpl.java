package com.distributed.roomconsumer.Service.impl.userImpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.distributed.roomapi.model.User;
import com.distributed.roomapi.service.UserLoginService;
import com.distributed.roomconsumer.Service.resposity.UserQueryResposity;


@Service
@org.springframework.stereotype.Service
public class UserQueryServiceImpl implements UserQueryResposity {

    @Reference
    UserLoginService userLoginService;

    @Override
    public User selectUserById(Integer userId) {
        return userLoginService.selectUserById(userId);
    }
}
