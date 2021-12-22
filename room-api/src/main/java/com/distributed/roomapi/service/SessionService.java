package com.distributed.roomapi.service;

public interface SessionService {

    /**
     * distributed system session should be saved in the third-party components(resdis)
     * @param userId user id
     * @return sessionId
     */
    String addSession2Redis(Integer userId, Long timeValue);

    /**
     * delete session from redis
     */
    Long deleteSessionFromRedis(Integer userId);

    /**
     * Count the current number of session
     * @return Long
     */
    Long currentSessionSavedNum();

    /**
     * verify key in redis
     * @param userId param + userid
     * @return string
     */
    String getSessionValue(String userId);

    /**
     * update session time
     * @return ttl
     */
    Long expireSession(String userId, Long timeValue);

}
