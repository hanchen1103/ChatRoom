package com.distributed.roomprovider.service.File;

import com.distributed.roomapi.model.File;
import com.distributed.roomprovider.service.impl.ProfileImpl;
import com.distributed.roomprovider.service.impl.UserLoginImpl;
import com.distributed.roomprovider.util.newProjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.UUID;

@Service
@com.alibaba.dubbo.config.annotation.Service
public class FileService implements com.distributed.roomapi.service.FileService {

    @Value("${file.path-centos}")
    String filePath;

    @Value("${file.url-web}")
    String fileUrl;

    @Value("${file.port}")
    String filePort;

    @Value("${file.suffix-centos}")
    String fileSuffix;

    @Autowired
    UserLoginImpl userService;

    @Autowired
    ProfileImpl profileImpl;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void picTypeFileUpload(File file, Integer userId) throws IOException {
        if(file == null || file.getMultipartFile().isEmpty()) {
            throw new FileNotFoundException();
        }
        MultipartFile multipartFile = file.getMultipartFile();
        String fileName = multipartFile.getOriginalFilename();
        assert fileName != null;
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        if(!newProjectUtil.isPic(suffixName)) {
            throw new FileSystemException("not correct type");
        }
        fileName = UUID.randomUUID() + suffixName;
        profileImpl.updateHeadUrl("http://" + fileUrl + ":" + filePort + fileSuffix
                + fileName, userId);
        java.io.File dest = new java.io.File(filePath + fileName);
        if(!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        multipartFile.transferTo(dest);
    }
}
