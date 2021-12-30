package com.distributed.roomapi.service;

import com.distributed.roomapi.model.User;

public interface UserQueryService {

    User selectUserById(Integer userId);

}
