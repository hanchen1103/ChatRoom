package com.distributed.roomconsumer.Service.resposity;

import com.distributed.roomapi.model.File;
import com.distributed.roomapi.model.Profile;

import java.io.IOException;

public interface ProfileRespoisty {

    Integer addProfile(Profile profile);

    Profile selectByUserId(Integer userId);

    void updateProfile(Profile profile);

    void updateHeadUrl(File file, Integer userId) throws IOException;

}
