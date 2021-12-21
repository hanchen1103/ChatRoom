package com.distributed.roomprovider.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class JedisAdapterForSession implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(JedisAdapter.class);

    private JedisPool jedisPool;

    @Override
    public void afterPropertiesSet() throws Exception {
        jedisPool = new JedisPool("redis://127.0.0.1:6379/5");
    }

    public Long setnx(String key, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.setnx(key, value);
        } catch (Exception e) {
            logger.error("redis error " + e.getMessage());
        }
        return null;
    }

    public String setex(String key, String value, Long timeValue) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.setex(key, timeValue, value);
        } catch (Exception e) {
            logger.error("redis error " + e.getMessage());
        }
        return null;
    }

    public String get(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.get(key);
        } catch (Exception e) {
            logger.error("redis error " + e.getMessage());
        }
        return null;
    }

    public Long del(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.del(key);
        } catch (Exception e) {
            logger.error("redis error " + e.getMessage());
        }
        return null;
    }

    public Long dbsize() {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.dbSize();
        } catch (Exception e) {
            logger.error("redis error " + e.getMessage());
        }
        return null;
    }




}
