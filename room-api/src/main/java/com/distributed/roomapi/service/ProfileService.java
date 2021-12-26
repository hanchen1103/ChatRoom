package com.distributed.roomapi.service;

import com.distributed.roomapi.model.Profile;

public interface ProfileService {

    Profile getProfileByUserId(Integer userId);

    Integer addProfile(Profile profile);

    void updateProfile(Profile profile);

    void updateHeadUrl(String headUrl, Integer userId);
}
