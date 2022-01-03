package com.distributed.roomconsumer.Service.impl.userImpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.distributed.roomapi.model.Profile;
import com.distributed.roomapi.model.User;
import com.distributed.roomapi.service.SessionService;
import com.distributed.roomapi.service.UserLoginService;
import com.distributed.roomconsumer.Service.resposity.UserLoginResposity;
import com.distributed.roomconsumer.config.MQConfig.KafkaProducer;
import com.distributed.roomconsumer.responsebody.LoginSessionResponseBody;
import com.distributed.roomconsumer.util.newProjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@Service
@org.springframework.stereotype.Service
public class UserLoginByAccoountServiceImpl implements UserLoginResposity {

    @Reference
    UserLoginService userResposity;

    @Reference
    SessionService sessionService;

    @Autowired
    KafkaProducer kafkaProducer;


    @Value("${file.suffix-mac}")
    String suffixName;

    @Value("${file.port}")
    String port;

    @Value("${file.url-local}")
    String localUrl;

    @Override
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
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
        Integer userId = userResposity.addUser(user);
        String res = sessionService.addSession2Redis(userId, expireTime);
        LoginSessionResponseBody loginSessionResponseBody = new LoginSessionResponseBody();
        loginSessionResponseBody.setUser(user);
        loginSessionResponseBody.setToken(res);
        afterRegGenerateProfile(userId);
        return loginSessionResponseBody;
    }

    @Override
    public void logout(Integer userId) {
        sessionService.deleteSessionFromRedis(userId);
    }

    public void afterRegGenerateProfile(Integer userId) {
        Profile profile = new Profile();
        profile.setCreateDate(new Date());
        profile.setUserId(userId);
        profile.setNickName("user" + UUID.randomUUID().toString().substring(0, 6));
        profile.setHeadUrl(localUrl + ":" + port + suffixName + new Random().nextInt(6) + ".jpeg");
        kafkaProducer.addUserProfileTopic(profile);
    }
}
