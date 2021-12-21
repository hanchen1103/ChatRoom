package com.distributed.roomapi.service;

public interface SessionService {

    /**
     * distributed system session should be saved in the third-party components(resdis)
     * @param userId user id
     * @return 1 or 0
     */
    Integer addSession2Redis(Integer userId, Long timeValue);

    /**
     * delete session from redis
     */
    Long deleteSessionFromRedis(Integer userId);

    /**
     * Count the current number of session
     * @return Long
     */
    Long currentSessionSavedNum();
}
