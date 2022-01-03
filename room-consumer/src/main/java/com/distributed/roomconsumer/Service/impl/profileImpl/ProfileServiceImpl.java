package com.distributed.roomconsumer.Service.impl.profileImpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.distributed.roomapi.model.Profile;
import com.distributed.roomapi.service.ProfileService;
import com.distributed.roomconsumer.Service.resposity.ProfileRespoisty;
import org.springframework.stereotype.Service;

@com.alibaba.dubbo.config.annotation.Service
@Service
public class ProfileServiceImpl implements ProfileRespoisty {

    @Reference
    ProfileService profileService;


    @Override
    public Integer addProfile(Profile profile) {
        if(profile == null || profile.getUserId() == null) {
            throw new NullPointerException("profile or profile.userId exception");
        }
        return profileService.addProfile(profile);
    }

    @Override
    public Profile selectByUserId(Integer userId) {
        if(userId == null) {
            throw new NullPointerException("userId exception");
        }
        return profileService.getProfileByUserId(userId);
    }

    @Override
    public void updateProfile(Profile profile) {
        if(profile == null || profile.getUserId() == null) {
            throw new NullPointerException("profile or profile.userId exception");
        }
        profileService.updateProfile(profile);
    }

    @Override
    public void updateHeadUrl(String headUrl, Integer userId) {
        if(headUrl == null || userId == null) {
            throw new NullPointerException("headUrl or userId param exception");
        }
        profileService.updateHeadUrl(headUrl, userId);
    }
}
