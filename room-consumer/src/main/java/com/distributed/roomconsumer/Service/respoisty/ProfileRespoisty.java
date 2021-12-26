package com.distributed.roomconsumer.Service.respoisty;

import com.distributed.roomapi.model.Profile;

public interface ProfileRespoisty {


    Integer addProfile(Profile profile);

    Profile selectByUserId(Integer userId);

    void updateProfile(Profile profile);

    void updateHeadUrl(String headUrl, Integer userId);

}
