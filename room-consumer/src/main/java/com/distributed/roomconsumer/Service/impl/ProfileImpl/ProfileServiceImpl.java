package com.distributed.roomconsumer.Service.impl.ProfileImpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.distributed.roomapi.model.Profile;
import com.distributed.roomapi.service.ProfileService;
import com.distributed.roomconsumer.Service.resposity.ProfileRespoisty;

@Service
@org.springframework.stereotype.Service
public class ProfileServiceImpl implements ProfileRespoisty {

    @Reference
    ProfileService profileService;

    public Integer addProfile(Profile profile) {
         profileService.addProfile(profile);
         return profile.getId();
    }

    public Profile selectByUserId(Integer userId) {
        return profileService.getProfileByUserId(userId);
    }

    public void updateProfile(Profile profile) {
        profileService.updateProfile(profile);
    }

    public void updateHeadUrl(String headUrl, Integer userId) {
        profileService.updateHeadUrl(headUrl, userId);
    }

}
