package com.distributed.roomprovider.service.impl;

import com.distributed.roomapi.model.User;
import com.distributed.roomapi.service.UserService;
import com.distributed.roomprovider.mapper.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@com.alibaba.dubbo.config.annotation.Service
public class UserImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Override
    public User selectUserById(Integer userId) {
        if(userId == null) {
            throw new NullPointerException();
        }
        return userDAO.selectByuserId(userId);
    }

    @Override
    public User selectUserByAccount(String account) {
        if(account == null) {
            throw new NullPointerException();
        }
        return userDAO.selectByUserAccount(account);
    }

    @Override
    public void updateUserPassword(String password, Integer userId) {
        if(password == null || userId == null) {
            throw new NullPointerException();
        }
        userDAO.updatePassword(password, userId);
    }

    @Override
    public Integer addUser(User user) {
        if(user == null) {
            throw new NullPointerException();
        }
        userDAO.addUser(user);
        return user.getId();
    }
}
