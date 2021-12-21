package com.distributed.roomprovider.service.RedisService;

import com.distributed.roomprovider.util.JedisAdapter;
import com.distributed.roomprovider.util.JedisAdapterForSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@com.alibaba.dubbo.config.annotation.Service
public class SessionService implements com.distributed.roomapi.service.SessionService {

    @Autowired
    JedisAdapterForSession jedisAdapterForSession;


    public static final String EXPIRE_TIME_PARAM = "USER-SESSION";


    @Override
    public Integer addSession2Redis(Integer userId, Long timeValue) {
        if(userId == null || timeValue == null) {
            throw new NullPointerException("param null");
        }
        return jedisAdapterForSession.setpx(EXPIRE_TIME_PARAM + userId, String.valueOf(userId), timeValue).equals("OK") ? 1 : -1;
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
}
