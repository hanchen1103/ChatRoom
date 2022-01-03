package com.distributed.roomconsumer.Service.impl.profileImpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.distributed.roomapi.model.File;
import com.distributed.roomapi.model.Profile;
import com.distributed.roomapi.service.FileService;
import com.distributed.roomapi.service.ProfileService;
import com.distributed.roomconsumer.Service.resposity.ProfileRespoisty;
import com.distributed.roomconsumer.util.newProjectUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.UUID;

@com.alibaba.dubbo.config.annotation.Service
@Service
public class ProfileServiceImpl implements ProfileRespoisty {

    @Value("${file.path-mac}")
    String filePath;

    @Value("${file.url-local}")
    String fileUrl;

    @Value("${file.port}")
    String filePort;

    @Value("${file.suffix-mac}")
    String fileSuffix;

    @Reference
    ProfileService profileService;

    @Reference
    FileService fileService;

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
    public void updateHeadUrl(File file, Integer userId) throws IOException {
        if(userId == null) {
            throw new NullPointerException("headUrl or userId param exception");
        }
        if(file == null) {
            throw new FileNotFoundException();
        }
        upload(file.getMultipartFile(), userId);
    }

    public void upload(MultipartFile multipartFile, Integer userId) throws IOException {
        if(multipartFile == null || multipartFile.isEmpty()) {
            throw new FileNotFoundException();
        }
        String fileName = multipartFile.getOriginalFilename();
        assert fileName != null;
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        if(!newProjectUtil.isPic(suffixName)) {
            throw new FileSystemException("not correct type");
        }
        fileName = UUID.randomUUID() + suffixName;
        profileService.updateHeadUrl("http://" + fileUrl + ":" + filePort + fileSuffix
                + fileName, userId);
        java.io.File dest = new java.io.File(filePath + fileName);
        if(!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        multipartFile.transferTo(dest);
    }
}
