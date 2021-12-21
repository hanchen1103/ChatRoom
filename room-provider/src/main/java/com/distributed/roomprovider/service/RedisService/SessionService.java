package com.distributed.roomprovider.service.RedisService;

import com.distributed.roomprovider.util.JedisAdapterForSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@com.alibaba.dubbo.config.annotation.Service
public class SessionService implements com.distributed.roomapi.service.SessionService {

    @Autowired
    JedisAdapterForSession jedisAdapterForSession;


    public static final String EXPIRE_TIME_PARAM = "USER-SESSION";


    @Override
    public String addSession2Redis(Integer userId, Long timeValue) {
        if(userId == null || timeValue == null) {
            throw new NullPointerException("param null");
        }
        String value = UUID.randomUUID().toString().substring(16);
        jedisAdapterForSession.setex(EXPIRE_TIME_PARAM + userId, value, timeValue);
        return value;
    }

    @Override
    public Long deleteSessionFromRedis(Integer userId) {
        if(userId == null) {
            throw new NullPointerException("param null");
        }
        return jedisAdapterForSession.del(EXPIRE_TIME_PARAM + userId);
    }

    @Override
    public Long currentSessionSavedNum() {
        return jedisAdapterForSession.dbsize();
    }

    @Override
    public String getSessionValue(String userId) {
        if(userId == null) {
            throw new NullPointerException("param null");
        }
        String sessionKey = EXPIRE_TIME_PARAM + userId;
        return jedisAdapterForSession.get(sessionKey);
    }
}
