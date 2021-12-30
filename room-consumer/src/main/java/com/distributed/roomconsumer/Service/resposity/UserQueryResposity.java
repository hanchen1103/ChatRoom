package com.distributed.roomconsumer.Service.resposity;

import com.distributed.roomapi.model.User;

public interface UserQueryResposity {

    User selectUserById(Integer userId);
}
