package com.distributed.roomconsumer.Service.respoisty;

import com.distributed.roomapi.model.User;

public interface UserRespo {

    User login(String account, String password);

    User register(String account, String password);

}
