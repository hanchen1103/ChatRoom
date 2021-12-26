package com.distributed.roomprovider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.distributed.roomapi.model.Profile;
import com.distributed.roomapi.service.ProfileService;
import com.distributed.roomprovider.mapper.ProfileDAO;

import javax.annotation.Resource;

@Service
@org.springframework.stereotype.Service
public class ProfileImpl implements ProfileService {

    @Resource
    ProfileDAO profileDAO;

    @Override
    public Profile getProfileByUserId(Integer userId) {
        if(userId == null) {
            throw new NullPointerException("param Exception");
        }
        return profileDAO.selectByUserId(userId);
    }

    @Override
    public Integer addProfile(Profile profile) {
        if(profile == null) {
            throw new NullPointerException("param Exception");
        }
        return profileDAO.addProfile(profile);
    }

    @Override
    public void updateProfile(Profile profile) {
        if(profile == null) {
            throw new NullPointerException("param Exception");
        }
        profileDAO.uodateProfile(profile);
    }

    @Override
    public void updateHeadUrl(String headUrl, Integer userId) {
        if(headUrl == null) {
            throw new NullPointerException("param Exception");
        }
        profileDAO.updateHeadUrl(headUrl, userId);
    }
}
