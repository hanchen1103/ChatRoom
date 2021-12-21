package com.distributed.roomapi.service;

import com.distributed.roomapi.model.User;

/**
 * user services collection
 */
public interface UserResposity {

    /**
     * query user by id
     * @param userId user id
     * @return user
     */
    User selectUserById(Integer userId);

    /**
     * query user by account
     * @param account user account
     * @return user
     */
    User selectUserByAccount(String account);

    /**
     * update user password
     * @param password user password
     * @param userId user id
     */
    void updateUserPassword(String password, Integer userId);
}
