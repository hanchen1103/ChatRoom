package com.distributed.roomprovider.service.RedisService;

import com.alibaba.fastjson.JSON;
import com.distributed.roomprovider.util.JedisAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@com.alibaba.dubbo.config.annotation.Service
public class WebSocketService implements com.distributed.roomapi.service.WebSocketService {

    @Autowired
    JedisAdapter jedisAdapter;

    private static final String WEB_SOCKET_PARAM = "WEB-SOCKET";

    @Override
    public Boolean containsSocket(Integer userId) {
        return jedisAdapter.sismember(WEB_SOCKET_PARAM, String.valueOf(userId));
    }

    @Override
    public void addUserAndSocket2Redis(Integer userId, Object object) {
        String jsonObject = JSON.toJSON(object).toString();
        jedisAdapter.sadd(WEB_SOCKET_PARAM, String.valueOf(userId));
        jedisAdapter.set(WEB_SOCKET_PARAM + userId, jsonObject);
    }

    @Override
    public void deleteUserInRedis(Integer userId) {
        jedisAdapter.srem(WEB_SOCKET_PARAM, String.valueOf(userId));
        jedisAdapter.del(WEB_SOCKET_PARAM + userId);
    }

    @Override
    public String getSocketByUserId(Integer userId) {
        return jedisAdapter.get(WEB_SOCKET_PARAM + userId);
    }

    @Override
    public Long getSumOfSocket() {
        return jedisAdapter.scard(WEB_SOCKET_PARAM);
    }
}
